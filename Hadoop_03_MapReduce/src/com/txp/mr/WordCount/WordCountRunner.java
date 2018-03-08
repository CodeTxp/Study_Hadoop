package com.txp.mr.WordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.txp.mr.WordCount.WordCountMapper;
import com.txp.mr.WordCount.WordCountReducer;
import com.txp.mr.WordCount.WordCountRunner;
import com.txp.util.HdfsUtil;

public class WordCountRunner implements Tool {

	private Configuration conf = null;

	@Override
	public void setConf(Configuration that) {
		this.conf = that;
		this.conf.set("fs.defaultFS", "hdfs://192.168.8.101:9000");
	}

	@Override
	public Configuration getConf() {
		return this.conf;
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = this.getConf();
		Job job = Job.getInstance(conf, "wordcount");
		// 1. 输入
		FileInputFormat.addInputPath(job, new Path("/input/"));
		// 2. map
		job.setMapperClass(WordCountMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		// 3. shuffle
		// 4. reduce
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		// 5. output
	    HdfsUtil.deleteFile("/beifeng/1");
		FileOutputFormat.setOutputPath(job, new Path("/beifeng/1"));
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		// 运行
		ToolRunner.run(new WordCountRunner(), args);
	}
}
