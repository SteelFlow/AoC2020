import utils.LoadInput

object Funcs {
  def addTwo(input: List[Int], numberToFind: Int, element: Int) = {
    if (input.contains(numberToFind)) {
      Some(element * numberToFind)
    } else {
      None
    }
  }
}

object AoC01 extends App {
  val input = LoadInput
    .load01()
    .map(s => s.toInt)

  def getResult(
      input: List[Int],
      numbersToAdd: Int,
      sumNumber: Int
  ): Option[Int] = {

    input
      .flatMap(element => {
        val numberToFind = sumNumber - element
        if (numbersToAdd == 2) {
          Funcs.addTwo(input, numberToFind, element)
        } else {
          val recursiveResult =
            getResult(input, numbersToAdd - 1, numberToFind)

          recursiveResult match {
            case Some(value) =>
              Some(value * element)
            case None => None
          }

        }
      }) match {
      case Nil       => None
      case head :: _ => Some(head)
    }
  }

  val resultA = getResult(input, 2, 2020)
  val resultB = getResult(input, 3, 2020)

  println(s"Advent of Code 2020 01 part 1: $resultA")
  println(s"Advent of Code 2020 01 part 2: $resultB")
}
