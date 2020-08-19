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
      // 10秒一个滑动窗口
      .timeWindow(Time.seconds(6),Time.seconds(2))
      //.timeWindow(Time.seconds(10))
      .sum(1)

    counts.print()
    env.execute()
  }
}
