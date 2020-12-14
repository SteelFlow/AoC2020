import utils.LoadInput
object AoC04 extends App {

  def getNextPassportBatch(
      input: List[String],
      fromIndex: Int,
      currentIndex: Int
  ): (Int, Int) = {

    if (currentIndex == input.length - 1) {
      (fromIndex, currentIndex)
    } else {
      input(currentIndex) match {
        case x if x.isBlank => (fromIndex, currentIndex)
        case _              => getNextPassportBatch(input, fromIndex, currentIndex + 1)
      }
    }
  }

}
