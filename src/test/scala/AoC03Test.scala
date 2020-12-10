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
    assert(AoC03.threeOver(line, 0) === 3)
    assert(AoC03.threeOver(line, 3) === 6)
    assert(AoC03.threeOver(line, 6) === 9)
    assert(AoC03.threeOver(line, 9) === 1)
    assert(AoC03.threeOver(line, 1) === 4)
    assert(AoC03.threeOver(line, 4) === 7)
    assert(AoC03.threeOver(line, 7) === 10)
    assert(AoC03.threeOver(line, 10) === 2)
    assert(AoC03.threeOver(line, 2) === 5)
    assert(AoC03.threeOver(line, 5) === 8)
    assert(AoC03.threeOver(line, 8) === 0)
  }

  test("isTree not tree returns false") {
    val line = input.head
    assert(AoC03.isTree(line, 0) === 0)
  }

  test("isTree tree returns false") {
    val line = input.head
    assert(AoC03.isTree(line, 2) === 1)
  }

  test("checkLines") {
    assert(AoC03.checkLines(input, 0, 0, 0) === 7)
  }
}
