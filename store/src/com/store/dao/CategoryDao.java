package com.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.store.domain.Category;

public interface CategoryDao {

	//查询全部分类信息
	List<Category> findAll() throws SQLException;
}
