package com.txp.mybatis.junit;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.txp.mybatis.dao.UserDao;
import com.txp.mybatis.dao.UserDaoImpl;
import com.txp.mybatis.pojo.User;

public class MybatisDaoTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws Exception {
		// 创建SqlSessionFactoryBuilder
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
		// 加载SqlMapConfig.xml配置文件
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		// 创建SqlsessionFactory
		this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
	}

	@Test
	public void testDao() throws Exception {
		// 创建DAO
		UserDao userDao = new UserDaoImpl(this.sqlSessionFactory);
		// 执行查询
		User user = userDao.queryUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testDaoDelete() throws Exception {
		// 创建DAO
		UserDao userDao = new UserDaoImpl(this.sqlSessionFactory);
		// 执行查询
		userDao.deleteUserById(1);
		
		//System.out.println("");
	}
}
