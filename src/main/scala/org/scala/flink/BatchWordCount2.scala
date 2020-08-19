package org.scala.flink

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.streaming.api.scala._

object BatchWordCount2 {
  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    val inputData = "E:\\git_workspace\\flink-demo\\src\\main\\resources\\words.txt"

    val text = env.readTextFile(inputData)
    val counts = text.flatMap(_.split(" "))
      .map((_, 1))
      .groupBy(0)
      .sum(1)

    counts.print()

  }
}
