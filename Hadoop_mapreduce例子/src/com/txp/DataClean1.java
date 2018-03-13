package com.txp;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;




public class DataClean1{
	/**
	 * 处理mapper类
	 * @author gerry
	 *
	 */
	static class DemoMapper extends Mapper<Object,Text,Text,Text> {
		 private static Text line=new Text();//每行数据
		@Override
		protected void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			line=value;
			context.write(line, new Text(""));
		}
	}

	/**
	 * 自定义的实现reducer
	 * @author gerry
	 *
	 */
	static class DemoReducer extends Reducer<Text,Text,Text,Text> {
		@Override
		protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			
			context.write(key, new Text(""));
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.101:9000");

		Job job = Job.getInstance(conf, "demo-job");
		job.setJarByClass(DataClean1.class);
		job.setMapperClass(DemoMapper.class);
		job.setReducerClass(DemoReducer.class);

		 //设置输出类型

	     job.setOutputKeyClass(Text.class);

	     job.setOutputValueClass(Text.class);

		
		
	
		// 输入输出路径
		FileInputFormat.addInputPaths(job, "/input1");
		FileOutputFormat.setOutputPath(job, new Path("/beifeng/output/" + System.currentTimeMillis()));

		// 提交
		job.waitForCompletion(true);
		
	}
}
