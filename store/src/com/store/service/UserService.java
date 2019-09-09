package com.store.service;

import java.sql.SQLException;

import com.store.domain.User;

public interface UserService {

	//用户注册
	void userRegist(User user) throws SQLException;
	
	//激活校验
	int active(String code) throws SQLException;
	
	//修改状态码
	void alterState(String code) throws SQLException;

	//删除code
	void updateCode(String code) throws SQLException;

	User userLogin(User user) throws SQLException;
	
}
