package utils

import scala.io.Source

object LoadInput {
  val load01 = () => {
    load(1)
  }

  val load02 = () => {
    load(2)
  }

  val load03 = () => {
    load(3)
  }

  val load04 = () => {
    load(4)
  }

  private def load(day: Int): List[String] = {
    val num = f"${day}%02d"
    val path =
      getClass().getClassLoader().getResource(s"$num.txt").getPath()
    Source.fromFile(path).getLines.toList;

  }
}
