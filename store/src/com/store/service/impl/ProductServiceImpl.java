package com.store.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.store.dao.ProductDao;
import com.store.dao.impl.ProductDaoImpl;
import com.store.domain.PageModel;
import com.store.domain.Product;
import com.store.service.ProductService;

public class ProductServiceImpl implements ProductService {

	ProductDao dao = new ProductDaoImpl();
	
	@Override
	public List<Product> findHots() throws SQLException {
		return dao.findHots();
	}

	@Override
	public List<Product> findNews() throws SQLException {
		return dao.findNews();
	}

	@Override
	public Product findProductByPid(String pid) throws SQLException {
		return dao.findProductByPid(pid);
	}

	@Override
	public PageModel findProductsWithCidAndPage(String cid, int curNum) throws SQLException {
		//1_创建PageModel对象 目的：计算分页参数
		
		//统计当前分类下商品个数
		int totalRecords = dao.findTotalRecords(cid);
		PageModel pm = new PageModel(curNum, totalRecords, 12);
		//2_关联集合
		List list = dao.findProductsWithCidAndPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("ProductServlet?method=findProductsWithCidAndPage&cid="+cid);
		return pm;
	}

}
