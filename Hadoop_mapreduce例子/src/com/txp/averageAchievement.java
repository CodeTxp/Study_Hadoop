package com.txp;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class averageAchievement {

	static class avgMap extends Mapper<LongWritable, Text, Text, IntWritable> {

		private Text word = new Text();

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			/*
			 * StringTokenizer itr = new StringTokenizer(value.toString());
			 * while (itr.hasMoreTokens()) { word.set(itr.nextToken()); String
			 * ScoreOne = itr.nextToken(); int ScoreInt =
			 * Integer.parseInt(ScoreOne); context.write(word,new
			 * IntWritable(ScoreInt));
			 */
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				String strName = itr.nextToken();
				String ScoreOne = itr.nextToken();
				int ScoreInt = Integer.parseInt(ScoreOne);
				context.write(new Text(strName), new IntWritable(ScoreInt));
			}
		}
	}

	static class avgReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			int scoreNum = 0;
			for (IntWritable val : values) {
				scoreNum++;
				sum += val.get();
			}
			int averageScore = sum / scoreNum;
			context.write(key, new IntWritable(averageScore));
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.101:9000");

		Job job = Job.getInstance(conf, "demo-job");
		job.setJarByClass(averageAchievement.class);
		job.setMapperClass(avgMap.class);
		job.setReducerClass(avgReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPaths(job, "/input1");
		FileOutputFormat.setOutputPath(job, new Path("/beifeng/output/" + System.currentTimeMillis()));
		// 提交
		job.waitForCompletion(true);

	}
}
