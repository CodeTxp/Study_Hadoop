package com.txp;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class dataSort {
	/**
	 * 处理mapper类
	 * @author gerry
	 *
	 */
	static class DemoMapper extends Mapper<Object,Text,IntWritable,IntWritable> {
		private static IntWritable data=new IntWritable();
		@Override
		protected void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			 String line=value.toString();

	         data.set(Integer.parseInt(line));

	         context.write(data, new IntWritable(1));
		}
	}

	/**
	 * 自定义的实现reducer
	 * @author gerry
	 *
	 */
	static class DemoReducer extends Reducer<IntWritable,IntWritable,IntWritable,IntWritable> {
		
		private static IntWritable linenum = new IntWritable(1);
		@Override
		protected void reduce(IntWritable key,Iterable<IntWritable> values,Context context)
				throws IOException, InterruptedException {
			
	                context.write(linenum, key);
	                linenum = new IntWritable(linenum.get()+1);  
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.101:9000");

		Job job = Job.getInstance(conf, "demo-job");
		job.setJarByClass(dataSort.class);
		job.setMapperClass(DemoMapper.class);
		job.setReducerClass(DemoReducer.class);

		 //设置输出类型

	     job.setOutputKeyClass(IntWritable.class);

	     job.setOutputValueClass(IntWritable.class);

		
		
	
		// 输入输出路径
		FileInputFormat.addInputPaths(job, "/input1");
		FileOutputFormat.setOutputPath(job, new Path("/beifeng/output/" + System.currentTimeMillis()));

		// 提交
		job.waitForCompletion(true);
		
	}
}
