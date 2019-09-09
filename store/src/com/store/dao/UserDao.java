package com.store.dao;

import java.sql.SQLException;

import com.store.domain.User;

public interface UserDao {
	//用户注册
	void userRegist(User user) throws SQLException;

	//激活校验
	int active(String code) throws SQLException;
	
	//修改状态码
	void alterState(String code) throws SQLException;

	//删除键激活码
	void updateCode(String code) throws SQLException;

	User userLogin(User user) throws SQLException;
}
