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

  def getField(passport: String, identifier: String) = {
    val start = passport.indexOf(identifier)
    val end = passport.indexOf(' ', start) match {
      case x if x == -1 => passport.length() 
      case x            => x 
    }

    passport.substring(start, end)
  }

  def validatePassport(passport: String) = {
    getField(passport, "eyr").matches("eyr:20(2\\d|30)") &&
    getField(passport, "iyr").matches("iyr:20(1\\d|20)") &&
    getField(passport, "byr").matches("byr:(19[2-9]\\d|200[0-2])") &&
    getField(passport, "hgt").matches("hgt:((59|(6\\d)|(7[0-6]))(in)|(1[5-8]\\d|19[0-3])(cm))") &&
    getField(passport, "hcl").matches("hcl:#([0-9a-f]){6}") &&
    getField(passport, "ecl").matches("ecl:(amb|blu|brn|gry|grn|hzl|oth)") &&
    getField(passport, "pid").matches("pid:(\\d){9}") 
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
    .filter(x => validatePassport(x)).length


  println(s"Advent of Code 2020 04 part 1: $res1")
  println(s"Advent of Code 2020 04 part 2: $res2")
}
