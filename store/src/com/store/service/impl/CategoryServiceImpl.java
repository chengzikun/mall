package com.store.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.store.dao.CategoryDao;
import com.store.dao.impl.CategoryDaoImpl;
import com.store.domain.Category;
import com.store.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> findAll() throws SQLException {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.findAll();
	}

}
