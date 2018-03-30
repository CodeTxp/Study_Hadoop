package com.txp.mybatis.dao;

import com.txp.mybatis.pojo.User;

public interface UserDao {
	/**
	 * 根据id查询用户
	 * 
	 * @param id
	 * @return
	 */
	User queryUserById(int id);
   
	void deleteUserById(int id);
	
}
