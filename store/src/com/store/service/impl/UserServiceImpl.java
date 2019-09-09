package com.store.service.impl;

import java.sql.SQLException;

import com.store.dao.UserDao;
import com.store.dao.impl.UserDaoImpl;
import com.store.domain.User;
import com.store.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void userRegist(User user) throws SQLException {
		UserDao dao = new UserDaoImpl();
		dao.userRegist(user);
		
	}

	@Override
	public int active(String code) throws SQLException {
		UserDao dao = new UserDaoImpl();
		return dao.active(code);
	}

	@Override
	public void alterState(String code) throws SQLException {
		UserDao dao = new UserDaoImpl();
		dao.alterState(code);
		
	}

	@Override
	public void updateCode(String code) throws SQLException {
		UserDao dao = new UserDaoImpl();
		dao.updateCode(code);
	}

	@Override
	public User userLogin(User user) throws SQLException {
		//可以利用异常在模块之间传递数据
		
		UserDao dao = new UserDaoImpl();
		User uu = dao.userLogin(user);
		if(null==uu) {
			throw new RuntimeException("密码有误");
		}else if(uu.getState()==0){
			throw new RuntimeException("用户未激活");
		}else {
			return uu;
		}
	}
}
