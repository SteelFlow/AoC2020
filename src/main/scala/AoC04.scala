import utils.LoadInput
object AoC04 extends App {

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

  def parsePassport(passport: String) {
    passport
  }

  val input = LoadInput.load04()

  val res1 = getBatchedPassports(input)
    .filter(passport => {
      passport.contains("byr") &&
        passport.contains("iyr") &&
        passport.contains("eyr") &&
        passport.contains("hgt") &&
        passport.contains("hcl") &&
        passport.contains("ecl") &&
        passport.contains("pid")
    })
    .length

  val res2 = getBatchedPassports(input)
    .filter(passport => {
      passport.contains("byr") &&
        passport.contains("iyr") &&
        passport.contains("eyr") &&
        passport.contains("hgt") &&
        passport.contains("hcl") &&
        passport.contains("ecl") &&
        passport.contains("pid")
    })
    .map(parsePassport)
    .length

  println(s"Advent of Code 2020 04 part 1: $res1")
  println(s"Advent of Code 2020 04 part 2: $res2")

}
