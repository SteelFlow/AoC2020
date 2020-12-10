import utils.LoadInput

object AoC03 extends App {

    def threeOver(line: String, currentIndex: Int) = {
        val maxIndex = line.length - 1
        val wantedIndex = currentIndex + 3
        if (wantedIndex <= maxIndex) {
            wantedIndex
        } else {
            wantedIndex - line.length
        }
    }

    def isTree(line: String, index: Int) = {
        line(index) == '#' match {
            case true => 1
            case false => 0
        }
    }

    def checkLines(input: List[String], currentRow: Int, currentCollumn: Int, treesHit: Int): Int = {
        val line = input(currentRow)
        if (currentRow == input.length - 1)
            treesHit + isTree(line, currentCollumn)
        else {
            checkLines(input, currentRow + 1, threeOver(line, currentCollumn), treesHit + isTree(line, currentCollumn))
        }
    }

    val resultA = checkLines(LoadInput.load03(), 0, 0, 0)
      
    println(s"Advent of Code 2020 03 part 1: $resultA")
}
