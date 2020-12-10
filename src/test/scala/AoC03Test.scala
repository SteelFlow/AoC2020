import org.scalatest.funsuite.AnyFunSuite
import scala.io.Source
import scala.util.ChainingSyntax

class AoC03Test extends AnyFunSuite {
  val input = utils.LoadInput.load03()

  test("check correct input") {
    assert(input.length === 11)
    assert(input(5) === "..#.##.....")
  }

}
