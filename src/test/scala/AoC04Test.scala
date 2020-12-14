import org.scalatest.funsuite.AnyFunSuite
import utils.LoadInput

class AoC04Test extends AnyFunSuite {

  test("Test get next batch first batch") {
    val input = LoadInput.load04()

    val res = AoC04.getNextPassportBatch(input)

    val expected =
      "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
    assert(res._1 === 0)
    assert(res._2 === 2)
    assert(res._3 === expected)

  }

  test("Test get next batch third batch") {
    val input = LoadInput.load04()

    val res = AoC04.getNextPassportBatch(input, 6, 6)

    val expected =
      "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm"

    assert(res._1 === 6)
    assert(res._2 === 10)
    assert(res._3 === expected)

  }

  test("Test get next batch last batch") {
    val input = LoadInput.load04()

    val res = AoC04.getNextPassportBatch(input, 11, 11)

    val expected =
      "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"

    assert(res._1 === 11)
    assert(res._2 === 12)
    assert(res._3 === expected)
  }

  test("Get batched passports") {
    val input = LoadInput.load04()

    val res = AoC04.getBatchedPassports(input)

    assert(res.length === 4)
  }
}
