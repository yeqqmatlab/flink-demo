package org.scala.flink

import org.apache.flink.streaming.api.scala._

object BatchWordCount {
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.fromElements(
      "Who's there?",
      "I think I hear them. Stand, ho! Who's there?")
    val counts = text.flatMap(_.toLowerCase.split("\\W+"))
      .filter(_.isEmpty)
      .map((_, 1))
      .keyBy(0)
      .sum(1)
    counts.print()



  }
}
