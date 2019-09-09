package com.store.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.dao.UserDao;
import com.store.domain.User;
import com.store.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public void userRegist(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		runner.update("insert into user values(?,?,?,?,?,?,?,?,?,?)", 
				user.getUid(),
				user.getUsername(),
				user.getPassword(),
				user.getName(),
				user.getEmail(),
				user.getTelephone(),
				user.getBirthday(),
				user.getSex(),
				user.getState(),
				user.getCode()
				);
	}

	@Override
	public int active(String code) throws SQLException {

		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		long result = (long)runner.query("select count(*) from user where  code= ?", 
				new ScalarHandler(),
				code
				);
		int r = (int)result;
		return r;
	}

	@Override
	public void alterState(String code) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		runner.update("UPDATE user SET state = 1 WHERE code = ?",code);
		System.out.println("set code");
	}

	@Override
	public void updateCode(String code) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		runner.update("update user set code = null where code = ?",code);
	}

	@Override
	public User userLogin(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query("select * from user where username = ? and password = ?", new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}

}
