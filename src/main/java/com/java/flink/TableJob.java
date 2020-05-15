package com.java.flink;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import java.util.List;

/**
 * created by yqq 2020/5/15
 */
public class TableJob {

    public static void main(String[] args) throws Exception{
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        BatchTableEnvironment tableEnv = BatchTableEnvironment.create(env);

        //source,这里读取CSV文件，并转换为对应的Class
        DataSet<TopScorers> csvInput = env
                .readCsvFile("G://flink_test.csv")
                .ignoreFirstLine()
                .pojoType(TopScorers.class,"rank","player","country","club","total_score","total_score_home","total_score_visit","point_kick");

        //将DataSet转换为Table
        Table topScore = tableEnv.fromDataSet(csvInput);
        //将topScore注册为一个表
        tableEnv.registerTable("top_score",topScore);
        //查询球员所在的国家，以及这些国家的球员（内援和外援）的总进球数
        Table groupedByCountry = tableEnv.sqlQuery("select country,sum(total_score) as sum_total_score from top_score group by country order by 2 desc");
        //转换回dataset
        DataSet<Result> result = tableEnv.toDataSet(groupedByCountry,Result.class);

        List<Result> list = result.collect();

        list.forEach(vo -> System.out.println(vo));

    }

    /**
     * 源数据的映射类
     */
    public static class TopScorers {
        /**
         * 排名，球员，国籍，俱乐部，总进球，主场进球数，客场进球数，点球进球数
         */
        public int rank;
        public String player;
        public String country;
        public String club;
        public int total_score;
        public int total_score_home;
        public int total_score_visit;
        public int point_kick;

    }

    /**
     * 统计结果对应的类
     */
    public static class Result {

        public String country;
        public int sum_total_score;

        @Override
        public String toString() {
            return "Result{" +
                    "country='" + country + '\'' +
                    ", sum_total_score=" + sum_total_score +
                    '}';
        }
    }
}
