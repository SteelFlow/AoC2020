import utils.LoadInput

object AoC03 extends App {

  def stepOver(line: String, currentIndex: Int, stepCount: Int) = {
    val maxIndex = line.length - 1
    val wantedIndex = currentIndex + stepCount
    if (wantedIndex <= maxIndex) {
      wantedIndex
    } else {
      wantedIndex - line.length
    }
  }

  def isTree(line: String, index: Int) = {
    line(index) == '#' match {
      case true  => 1
      case false => 0
    }
  }

  def checkLines(
      input: List[String],
      currentRow: Int,
      currentCollumn: Int,
      treesHit: Int,
      stepOverCount: Int,
      stepDownCount: Int
  ): Long = {
    val line = input(currentRow)
    if (currentRow == input.length - 1)
      treesHit + isTree(line, currentCollumn)
    else {
      checkLines(
        input,
        currentRow + stepDownCount,
        stepOver(line, currentCollumn, stepOverCount),
        treesHit + isTree(line, currentCollumn),
        stepOverCount,
        stepDownCount
      )
    }
  }

  val input = LoadInput.load03()
  val resultA = checkLines(input, 0, 0, 0, 3, 1)

  val reducer = (accumulated: Long, current: Long) => { accumulated * current }

  val resultB = List(
    checkLines(input, 0, 0, 0, 1, 1),
    checkLines(input, 0, 0, 0, 3, 1),
    checkLines(input, 0, 0, 0, 5, 1),
    checkLines(input, 0, 0, 0, 7, 1),
    checkLines(input, 0, 0, 0, 1, 2)
  ).reduce(reducer)

  println(s"Advent of Code 2020 03 part 1: $resultA")
  println(s"Advent of Code 2020 03 part 2: $resultB")

}
