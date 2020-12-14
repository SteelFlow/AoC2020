import org.scalatest.funsuite.AnyFunSuite
import utils.LoadInput

class AoC04Test extends AnyFunSuite {

  test("Test get next batch first batch") {
    val input = LoadInput.load04()

    val res = AoC04.getNextPassportBatch(input, 0, 0)

    assert(0 === res._1)
    assert(2 === res._2)

  }

  test("Test get next batch third batch") {
    val input = LoadInput.load04()

    val res = AoC04.getNextPassportBatch(input, 6, 6)

    assert(6 === res._1)
    assert(10 === res._2)

  }

  test("Test get next batch last batch") {
    val input = LoadInput.load04()

    val res = AoC04.getNextPassportBatch(input, 11, 11)

    assert(11 === res._1)
    assert(12 === res._2)

  }

}
