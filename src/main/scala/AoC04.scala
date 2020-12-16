import utils.LoadInput

import scala.annotation.tailrec
object AoC04 extends App {

  @tailrec
  def getNextPassportBatch(
      input: List[String],
      fromIndex: Int = 0,
      currentIndex: Int = 0
  ): (Int, Int, String) = {
    if (currentIndex == input.length) {
      (
        fromIndex,
        currentIndex - 1,
        input.slice(fromIndex, currentIndex).mkString(" ")
      )
    } else {
      input(currentIndex) match {
        case x if x.isBlank =>
          (
            fromIndex,
            currentIndex,
            input.slice(fromIndex, currentIndex).mkString(" ")
          )
        case _ => getNextPassportBatch(input, fromIndex, currentIndex + 1)
      }
    }
  }

  @tailrec
  def getBatchedPassports(
      input: List[String],
      startIndex: Int = 0,
      currentIndex: Int = 0,
      batchedPassportList: List[String] = List.empty
  ): List[String] = {
    if (startIndex >= input.length) {
      batchedPassportList
    } else {
      val batch = getNextPassportBatch(input, startIndex, currentIndex)
      getBatchedPassports(
        input,
        batch._2 + 1,
        batch._2 + 1,
        batchedPassportList :+ batch._3
      )
    }
  }

  val validateYear =
    (passport: String, passportField: String, min: Int, max: Int) => {
      val startIndex = passport.indexOf(passportField) + 4
      val endIndex = passport.indexOf(' ', startIndex) match {
        case x if x == -1 => passport.length() - 1
        case x            => x
      }
      passport
        .substring(
          startIndex,
          endIndex
        )
        .toIntOption match {
        case Some(x) if x >= min && x <= max => true
        case _                               => false
      }
    }

  def validateHeight(passport: String) = {
    val startIndex = passport.indexOf("hgt:") + 4
    val endIndex = passport.indexOf(' ', startIndex) match {
      case x if x == -1 => passport.length() - 2
      case x            => x - 2
    }
    val typ = passport.substring(endIndex, endIndex + 2)

    typ match {
      case "cm" =>
        passport
          .substring(startIndex, endIndex)
          .toIntOption match {
          case Some(x) if x >= 150 && x <= 193 => true
          case _                               => false
        }
      case "in" =>
        passport
          .substring(startIndex, endIndex)
          .toIntOption match {
          case Some(x) if x >= 59 && x <= 76 => true
          case _ => false
        }
      case _ => false
    }
  }

  def validatePassport(passport: String) {
    validateYear(passport, "byr", 1920, 2002) &&
    validateYear(passport, "iyr", 2010, 2020) &&
    validateYear(passport, "eyr", 2020, 2002) &&
    validateHeight(passport)
    passport
  }

  def filterPassportsWithMissingFields(batchedPassports: List[String]) = {
    batchedPassports.filter(passport => {
      passport.contains("byr") &&
        passport.contains("iyr") &&
        passport.contains("eyr") &&
        passport.contains("hgt") &&
        passport.contains("hcl") &&
        passport.contains("ecl") &&
        passport.contains("pid")
    })
  }

  val input = LoadInput.load04()

  val res1 = filterPassportsWithMissingFields(getBatchedPassports(input)).length

  val res2 = filterPassportsWithMissingFields(getBatchedPassports(input))
    .map(validatePassport)
    .length

  println(s"Advent of Code 2020 04 part 1: $res1")
  println(s"Advent of Code 2020 04 part 2: $res2")
}
