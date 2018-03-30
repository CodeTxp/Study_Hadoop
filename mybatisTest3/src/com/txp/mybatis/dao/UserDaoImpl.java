package com.txp.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.txp.mybatis.pojo.User;

public class UserDaoImpl implements UserDao {

	// 注入
	private SqlSessionFactory sqlSessionFactory;

	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public User queryUserById(int id) {
		// 创建SqlSession
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		// 执行查询逻辑
		User user = sqlSession.selectOne("test.queryUserById", id);
		// 释放资源
		sqlSession.close();

		return user;
	}

	@Override
	public void  deleteUserById(int id) {
		// 创建SqlSession
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		// 执行查询逻辑
		sqlSession.delete("test.deleteUserById", id);
		
		// 释放资源
		sqlSession.close();
	}

}
