package com.txp.mr.index;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ReverseIndexRunner {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.101:9000");
		Job job = Job.getInstance(conf, "reverse_index");

		FileInputFormat.setInputPaths(job, "/input");
		job.setJarByClass(ReverseIndexRunner.class);
		job.setMapperClass(ReverseIndexMapper.class);
		job.setReducerClass(ReverseIndexReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileOutputFormat.setOutputPath(job, new Path("/beifeng/index/" + System.currentTimeMillis()));
		job.waitForCompletion(true);
	}
}
