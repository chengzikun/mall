package com.store.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.store.dao.OrderDao;
import com.store.dao.impl.OrderDaoImpl;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.PageModel;
import com.store.domain.User;
import com.store.service.OrderService;
import com.store.utils.JDBCUtils;

public class OrderServiceImpl implements OrderService {
	
	OrderDao orderDao=new OrderDaoImpl();

	@Override
	public List<Order> findAllOrders() throws Exception {
		return orderDao.findAllOrders();
	}

	@Override
	public List<Order> findAllOrders(String st) throws Exception {
		return orderDao.findAllOrders(st);
	}

	@Override
	public void saveOrder(Order order) throws SQLException {
		
		Connection conn=null;
		try {
			//获取连接
			conn=JDBCUtils.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//保存订单
			
			orderDao.saveOrder(conn,order);
			//保存订单项
			for (OrderItem item : order.getList()) {
				orderDao.saveOrderItem(conn,item);	
			}
			//提交
			conn.commit();
		} catch (Exception e) {
			//回滚
			conn.rollback();
		}
	}

	@Override
	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
		//1_创建PageModel对象,目的:计算并且携带分页参数
		//select count(*) from orders where uid=?
		int totalRecords=orderDao.getTotalRecords(user);
		PageModel pm=new PageModel(curNum, totalRecords, 3);
		//2_关联集合  select * from orders where uid=? limit ? ,?
		List list=orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		return orderDao.findOrderByOid(oid);
		
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		orderDao.updateOrder(order);
		
	}
}