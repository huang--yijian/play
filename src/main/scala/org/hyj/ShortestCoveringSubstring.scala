package org.hyj

import com.sun.xml.internal.org.jvnet.fastinfoset.RestrictedAlphabet

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * labuladong 1.7.1 最小覆盖子串
 */
object ShortestCoveringSubstring {

  def main(args: Array[String]): Unit = {
    println("hello")
    getShortestCoveringSubstring("ADBECFEBANC", List('A','B','C'))
//    getShortestCoveringSubstring("AQBQCQBA", List('A','B','C'))
//    println(s"output:\n${output.mkString("\n")}")
//    println(s"answer:\n${output.minBy(s => s.length)}")
    println(s"answer: \n$best")
  }

  private val stats = mutable.HashMap[Char,Int]();
//  private val output = ArrayBuffer[String]()
  private var best:String = null;

  private def getShortestCoveringSubstring(data:String, alphabet:List[Char]) = {
    var left = 0;
    for (right <- 0 to data.length-1) {
      stats(data(right)) = stats.getOrElse(data(right), 0) + 1
      if (isCoveringAllLetters(alphabet)) {
        // If requirement is not met, the last left is the best for the current right.
        while (left < right && isCoveringAllLetters(alphabet)) {
          if (best == null || (right - left) < best.length) {
            best = data.slice(left,right+1)
          }
          stats(data(left)) = stats.getOrElse(data(left), 0) - 1
          left = left + 1;
        }
      }
    }
  }


  private def isCoveringAllLetters(alphabet: List[Char]): Boolean = {
    alphabet.forall(char => stats.getOrElse(char,0) >0)
  }

  /**
   * A version not using shortestLeftRight
   *
   * private def getShortestCoveringSubstring(data:String, alphabet:List[Char]) = {
    var left = 0;
    for (right <- 0 to data.length-1) {
      stats(data(right)) = stats.getOrElse(data(right), 0) + 1
      if (isCoveringAllLetters(alphabet)) {
        // If requirement is not met, the last left is the best for the current right.
        var break = false
        while (left < right && !break) {
          stats(data(left)) = stats.getOrElse(data(left), 0) - 1
          left = left + 1;
          if (isCoveringAllLetters(alphabet)) {

          } else {
            // Revert increase of left
            left = left - 1;
            stats(data(left)) = stats.getOrElse(data(left), 0) + 1
            break = true
            output += data.slice(left, right+1)
          }
        }
      }
    }
  }
   */
}
