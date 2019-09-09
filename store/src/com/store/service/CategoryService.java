package com.store.service;

import java.sql.SQLException;
import java.util.List;

import com.store.domain.Category;

public interface CategoryService {
	
	//查询全部分类信息
	List<Category> findAll() throws SQLException;
}
