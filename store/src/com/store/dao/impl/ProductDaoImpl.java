package com.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.dao.ProductDao;
import com.store.domain.Product;
import com.store.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findHots() throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query("select * from  product where pflag=0 and is_hot=1 order by pdate desc limit 0,9", new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findNews() throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query("select * from  product where pflag=0 order by pdate desc limit 0,9", new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product findProductByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query("select * from product where pid=?",new BeanHandler<Product>(Product.class),pid);
	}

	@Override
	public int findTotalRecords(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long)runner.query("select count(*) from product where cid = ?", new ScalarHandler(),cid);
		return num.intValue();
	}

	@Override
	public List<Product> findProductsWithCidAndPage(String cid, int startIndex, int pageSize) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query("select * from product where cid=? limit ?,?", new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
		
	}

}
