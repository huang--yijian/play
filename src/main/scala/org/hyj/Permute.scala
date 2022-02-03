package org.hyj

import scala.collection.mutable.ArrayBuffer

object Permute {
  private var output:ArrayBuffer[String] = ArrayBuffer()

  def main(args:Array[String]): Unit = {
      println("hello")
      permute(ArrayBuffer(), List('a', 'b'))
      println(s"Output ${output.mkString("\n")}")
  }

  // Backtrace version of permute
  def permute(path:ArrayBuffer[Char], choices:List[Char]): Unit = {
    if (choices.isEmpty) {
      output += path.mkString("")
      return
    }

    for (i <- 0 to choices.length - 1) {
      val c = choices(i)
      path.append(c)
      val newChoices = choices.filter(ch => ch != c)
      permute(path, newChoices)
      path.remove(path.length - 1)
    }
  }

}
