import org.scalatest.funsuite.AnyFunSuite
import scala.io.Source
import scala.util.ChainingSyntax

class AoC03Test extends AnyFunSuite {
  val input = utils.LoadInput.load03()

  test("check correct input") {
    assert(input.length === 11)
    assert(input(5) === "..#.##.....")
  }

  test("threeOver with overflow returns correct") {
    val line = input.head //..##.......
    assert(AoC03.stepOver(line, 0, 3) === 3)
    assert(AoC03.stepOver(line, 7, 3) === 10)
    assert(AoC03.stepOver(line, 10, 1) === 0)
    assert(AoC03.stepOver(line, 8, 5) === 2)
  }

  test("isTree not tree returns false") {
    val line = input.head
    assert(AoC03.isTree(line, 0) === 0)
  }

  test("isTree tree returns false") {
    val line = input.head
    assert(AoC03.isTree(line, 2) === 1)
  }

  test("checkLines 3 1 = 7") {
    assert(AoC03.checkLines(input, 0, 0, 0, 3, 1) === 7)
  }

  test("checkLines 1 1 = 2") {
    assert(AoC03.checkLines(input, 0, 0, 0, 1, 1) === 2)
  }

}
