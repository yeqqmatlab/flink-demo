package org.scala.flink

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

object StreamWordCount {

  def main(args: Array[String]): Unit = {


    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val text = env.socketTextStream("ip248", 9999)
    val counts = text.flatMap(_.split("\\W+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)

    //设置并行度
    counts.print().setParallelism(1)

    env.execute()

  }
}
