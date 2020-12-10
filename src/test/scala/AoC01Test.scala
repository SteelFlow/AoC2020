import org.scalatest.funsuite.AnyFunSuite

class AoC01Test extends AnyFunSuite {
  val input = List(2, 3, 4)

  test(
    "addTwo input contains numberToFind should return element * numberToFind"
  ) {
    val result = Funcs.addTwo(input, 3, 2)

    assert(result.head === 6)
  }

  test("addTwo input does not contains numberToFind should return None") {
    val result = Funcs.addTwo(input, 6, 2)

    assert(result.isEmpty)
  }
}
