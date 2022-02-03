package org.hyj

import scala.collection.mutable.ArrayBuffer

object MergeSort {

  def main(args: Array[String]): Unit = {
    println("hello")
    val data = ArrayBuffer(3,2,1,4,9,12,0
    )
    val sorted = sort(data,0,data.length-1)
    println(s"sorted: ${sorted.mkString(",")}")
  }

  private def sort(data:ArrayBuffer[Int], start:Int, end:Int):ArrayBuffer[Int] = {
    if (start == end ) {
      return ArrayBuffer(data(start))
    } else if (end - start == 1) {
      if (data(end) < data(start)) {
        return ArrayBuffer(data(end), data(start))
      } else {
        return ArrayBuffer(data(start), data(end))
      }
    }
    val middle = (start + end) / 2
    val array1 = sort(data, start, middle)
    val array2 = sort(data, middle + 1, end)
    merge(array1, array2)
  }

  private def merge(left:ArrayBuffer[Int], right:ArrayBuffer[Int]):ArrayBuffer[Int] = {
    var i = 0
    var j = 0
    val result = ArrayBuffer[Int]()
    while (true) {
      if (i >= left.length) {
        result ++= right.takeRight(right.length - j)
        return result
      } else if (j >= right.length) {
        result ++= left.takeRight(left.length - i)
        return result
      } else if (left(i) <= right(j)) {
        result += left(i)
        i = i + 1;
      } else {
        result += right(i)
        j = j + 1
      }
    }
    result
  }

  /**
   *
  1,3,5
  0,2,7,8
   */
}
