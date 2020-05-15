package org.scala.flink

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object WindowWordCount {
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val text = env.socketTextStream("ip248", 9999)
    val counts = text.flatMap(_.split("\\W+"))
      //.filter(_.isEmpty)
      .map((_,1))
      .keyBy(0)
      .timeWindow(Time.seconds(3))
      .sum(1)

    counts.print()
    env.execute()
  }
}
