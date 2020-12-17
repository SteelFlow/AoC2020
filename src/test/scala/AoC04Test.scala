import org.scalactic.TripleEqualsSupport
import utils.LoadInput
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class AoC04Test extends AnyFunSuite with Matchers {
  val input = LoadInput.load04()

  test("Test get next batch first batch") {
    val res = AoC04.getNextPassportBatch(input)

    val expected =
      "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"

    res._1 shouldEqual 0
    res._2 shouldEqual 2
    res._3 shouldEqual expected
  }

  test("Test get next batch third batch") {
    val res = AoC04.getNextPassportBatch(input, 6, 6)

    val expected =
      "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm"

    res._1 shouldEqual 6
    res._2 shouldEqual 10
    res._3 shouldEqual expected

  }

  test("Test get next batch last batch") {
    val res = AoC04.getNextPassportBatch(input, 11, 11)

    val expected =
      "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"

    res._1 shouldEqual 11
    res._2 shouldEqual 13
    res._3 shouldEqual expected
  }

  test("Get batched passports") {
    val res = AoC04.getBatchedPassports(input)

    res.length shouldEqual 7 
  }

  test("Filter passports with missing fields") {
    val passports = AoC04.getBatchedPassports(input)
    val filteredPassports = AoC04.filterPassportsWithMissingFields(passports)
    assert(filteredPassports.length === 5)
  }

  test("GetField first field in passport") {
    val passports = AoC04.getBatchedPassports(input)
    val filteredPassports = AoC04.filterPassportsWithMissingFields(passports)
    val field = AoC04.getField(filteredPassports.head, "ecl")
    field shouldEqual "ecl:gry"
  }

  test("GetField field in middle of passport") {
    val passports = AoC04.getBatchedPassports(input)
    val filteredPassports = AoC04.filterPassportsWithMissingFields(passports)
    val field = AoC04.getField(filteredPassports.head, "hcl")
    field shouldEqual "hcl:#fffffd"
  }

  test("GetField last field in passport") {
    val passports = AoC04.getBatchedPassports(input)
    val filteredPassports = AoC04.filterPassportsWithMissingFields(passports)
    val field = AoC04.getField(filteredPassports.head, "hgt")
    field shouldEqual "hgt:183cm"
  }
}
