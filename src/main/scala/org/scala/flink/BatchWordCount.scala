package org.scala.flink


import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.streaming.api.scala._

object BatchWordCount {
  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    val text = env.fromElements(
      "Who's there?",
      "I think I hear them. Stand, ho! Who's there?")
    val counts = text.flatMap(_.toLowerCase.split(" "))
      .filter(_.isEmpty)
      .map((_, 1))
      .groupBy(0)
      .sum(1)



    counts.print()

    println("aaaa")



  }
}
