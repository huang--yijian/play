package org.hyj

import scala.collection.mutable.ArrayBuffer

/**
 *
 * ABCD
 *
 *
 */

object Hello {
  def main(args:Array[String]) = {
    // println(permute("a").mkString("\n"))
    println(permute("abcd").mkString("\n"))
  }

  def permute(data:String):ArrayBuffer[String] = {
    assert(data.nonEmpty, s"Data to permute should be nonempty, invalid. data [$data]")
    // base case
    if (data.length == 1) {
      return ArrayBuffer(data)
    }
    println(s"step 1. {$data}")
    val permutations = permute(data.slice(0, data.length-1))
    val tail = data.charAt(data.length-1)
    val result = new ArrayBuffer[String]
    result ++= permutations
    result += tail.toString
    for (permutation <- permutations) {
      // TODO insert the new element
//      println(s"permutation of length [${(data.length)-1}]:\n${permutation}")
      result ++= addElement(tail,permutation)
    }
    result
  }


  private def addElement(element:Char, data:String): Array[String] = {
    assert(data.nonEmpty, s"Data is empty, invalid, in addElement().")

    val result = new ArrayBuffer[String]
    for (i <- 0 to data.length ) {
      val dataBuffer = new StringBuffer(data);
      val str = dataBuffer.insert(i,element).toString;
      result += str
    }
    result.toArray
  }
}
