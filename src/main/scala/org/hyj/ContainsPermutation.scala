package org.hyj

import scala.collection.mutable

object ContainsPermutation {
  def main(args: Array[String]): Unit = {
    println("hello")
    containsPermutation("GAQATBTQBGTQB","GQT")
  }

  /**
   * ABDCABCAC
   *
   * ABC
   */

  /**
   * 判断字符串S是否存在一个子串是T的一种全排列
   */
  private val stat:mutable.HashMap[Char,Int] = new mutable.HashMap()
  private def containsPermutation(s:String, t:String): Unit = {
    val need = t.toCharArray.groupBy(c =>c).map(tuple => Tuple2(tuple._1,tuple._2.length))
    for (right <- 0 to s.length - 1) {
      stat(s(right)) = stat.getOrElse(s(right),0) + 1
      val left = right - t.length + 1
      if (left - 1 >=0) {
        stat(s(left - 1)) = stat.getOrElse(s(left - 1),0) - 1
      }
      if (matchPermutation(need)) {
        println(s"Matching substring: ${s.slice(left, right+1)}")
      }
    }
  }

  private def matchPermutation(need:Map[Char,Int]): Boolean = {
    for ((k,v) <- need) {
      if (stat.getOrElse(k,-1) != v) {
        return false;
      }
    }
    for ((k,v) <- stat) {
      if (v != 0 && need.getOrElse(k,-1) != v) {
        return false;
      }
    }
    true
  }

}
