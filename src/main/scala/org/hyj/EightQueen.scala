package org.hyj

import org.hyj.Hello.permute

import scala.collection.mutable.ArrayBuffer

object EightQueen {
  // An array of solutions
  // Each solution is a String of 8 int, specifying the column index in each of all the 8 rows
  private val solutions:ArrayBuffer[String] = ArrayBuffer()

  def main(args:Array[String]) = {
    eightQueen(ArrayBuffer(), Array(0, 1,2,3,4,5,6,7))
    println(s"Solutions count:${solutions.size}")
    println(s"All solutions:\n${solutions.map(solution => solution.mkString("")).mkString("\n")}")
  }

  def eightQueen(path:ArrayBuffer[Int], nextChoices:Array[Int]): Unit = {
    if (nextChoices.isEmpty) {
      solutions += path.mkString("")
      return
    }
    for (c  <- nextChoices) {
      if (isValidChoice(path, c)) {
        path += c
        eightQueen(path, nextChoices.filter(ch=> ch != c))
        path.remove(path.length-1)
      }
    }
  }

  private def isValidChoice(path:ArrayBuffer[Int], choice:Int): Boolean = {
    if (path.contains(choice)) {
      // Same column
      return false
    }

    val currentRow = path.length
    val currentCol = choice
    for (row <- 0 to path.length-1) {
      val col = path(row)
      // Diagonal
      if (currentRow - row == currentCol - col) {
        return false
      }
      if (currentRow - row == col - currentCol) {
        return false
      }
    }
    true
  }
}
