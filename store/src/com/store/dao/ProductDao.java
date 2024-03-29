package com.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.store.domain.Product;

public interface ProductDao {

	List<Product> findHots() throws SQLException;

	List<Product> findNews() throws SQLException;

	Product findProductByPid(String pid) throws SQLException;

	int findTotalRecords(String cid) throws SQLException;

	List<Product> findProductsWithCidAndPage(String cid, int startIndex, int pageSize) throws SQLException;

}
