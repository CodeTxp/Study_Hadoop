package com.txp.mybatis.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.txp.mybatis.mapper.UserMapper;
import com.txp.mybatis.pojo.User;

public class junitTest {
	
	@Test
	public void testMapper() throws Exception {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//扫描
		 //UserMapper mapper = ac.getBean(UserMapper.class);
		//mapper动态代理
		UserMapper mapper = (UserMapper) ac.getBean("Mapper");
		
		User user = mapper.findUserById(1);
		System.out.println(user);
		
	}

}
