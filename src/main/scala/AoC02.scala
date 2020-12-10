import utils.LoadInput

object AoC02 extends App {
  case class ParsedLine(
      val min: Int,
      val max: Int,
      val validationChar: Char,
      val password: String
  )

  val parseLine = (line: String) => {
    val dash = line.indexOf('-')
    val validationChar = line.indexOf(':') - 1
    val min = line.substring(0, dash).toInt
    val max = line.substring(dash + 1, validationChar - 1).toInt
    val password = line.substring(validationChar + 3)

    ParsedLine(min, max, line.charAt(validationChar), password)
  }

  val input = LoadInput
    .load02()
    .map(s => parseLine(s))

  val validateA = (line: ParsedLine) => {
    line match {
      case x if !x.password.contains(x.validationChar)          => false
      case x if x.password.count(_ == x.validationChar) < x.min => false
      case x if x.password.count(_ == x.validationChar) > x.max => false
      case _                                                    => true
    }
  }

  val validateB = (line: ParsedLine) => {
    val charAtMin = line.password.charAt(line.min - 1)
    val charAtMax = line.password.charAt(line.max - 1)
    line match {
      case x if !x.password.contains(x.validationChar) => false
      case x
          if charAtMin == x.validationChar && charAtMax == x.validationChar =>
        false
      case x
          if charAtMin != x.validationChar && charAtMax == x.validationChar =>
        true
      case x
          if charAtMin == x.validationChar && charAtMax != x.validationChar =>
        true
      case _ => false
    }
  }

  val resultA = input
    .filter(pIn => validateA(pIn))
    .length

  val resultB = input
    .filter(pIn => validateB(pIn))
    .length

  println(s"Advent of Code 2020 02 part 1: $resultA")
  println(s"Advent of Code 2020 02 part 2: $resultB")
}
