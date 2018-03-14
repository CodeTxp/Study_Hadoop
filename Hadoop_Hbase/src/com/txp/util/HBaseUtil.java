package com.txp.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class HBaseUtil {
	/**
	 * 
	 * 获取HBASE的配置文件信息
	 * 
	 * @author
	 */
	public static Configuration getHBaseConfiguration(){
		Configuration conf=HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum","192.168.8.101");
		return conf;
	}
}
