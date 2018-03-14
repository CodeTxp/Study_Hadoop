package com.txp.Test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import com.txp.util.HBaseUtil;

public class TestHBaseAdmin {
	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {

		Configuration conf=HBaseUtil.getHBaseConfiguration();
		HBaseAdmin hbaseAdmin=new HBaseAdmin(conf);
		try{
			testCreateTable(hbaseAdmin);
			//testGetTableDescribe(hbaseAdmin);
			//testDeleteTable(hbaseAdmin);
		}finally{
			hbaseAdmin.close();
		}
	}
	
	/**		
	 * 
	 *测试创建table
	 * @throws IOException 
	 */
	static void testCreateTable(HBaseAdmin hbAdmin) throws IOException{
		TableName tableName=TableName.valueOf("test");
		if(hbAdmin.tableExists(tableName)){
			System.out.println("表存在");
		}else{
			HTableDescriptor htd=new HTableDescriptor(tableName);
			htd.addFamily(new HColumnDescriptor("f"));
			hbAdmin.createTable(htd);
			System.out.println("创建成功");
			
		}
		
	}
	/**
	 * 测试获取表信息
	 * 
	 * @param hbAdmin
	 * @throws IOException 
	 * @throws TableNotFoundException 
	 */
	static void testGetTableDescribe(HBaseAdmin hbAdmin) throws TableNotFoundException, IOException{
		//hdAdmin.createNamespace(NamespaceDescriptor.create("dd").build());
		TableName tableName=TableName.valueOf("test");
		if(hbAdmin.tableExists(tableName)){
			HTableDescriptor htd=hbAdmin.getTableDescriptor(tableName);
			System.out.println(htd);
		}else{
			System.out.println("表不存在");
		}
		
	}
	
	/**
	 * 测试删除信息
	 * 
	 * @param hbAdmin
	 * @throws IOException 
	 */
	static void testDeleteTable(HBaseAdmin hbAdmin) throws IOException{
		TableName name=TableName.valueOf("test");
		if(hbAdmin.tableExists(name)){
			if(hbAdmin.isTableEnabled(name)){
				hbAdmin.disableTable(name);
			}
			hbAdmin.deleteTable(name);
			System.out.println("删除成功");
		}else{
			System.out.println("表不存在");
		}
		
	}
}
