package com.store.service;

import java.sql.SQLException;
import java.util.List;

import com.store.domain.PageModel;
import com.store.domain.Product;

public interface ProductService {

	List<Product> findHots() throws SQLException;

	List<Product> findNews() throws SQLException;

	Product findProductByPid(String pid) throws SQLException;

	PageModel findProductsWithCidAndPage(String cid, int curNum) throws SQLException;

}
