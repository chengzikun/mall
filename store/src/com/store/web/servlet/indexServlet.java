package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.domain.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.service.impl.ProductServiceImpl;
import com.store.web.base.BaseServlet;

@WebServlet("/indexServlet")
public class indexServlet extends BaseServlet {
	
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			//调用业务层功能，获取全部分类信息，返回集合
//			CategoryService service = new CategoryServiceImpl();
//			List<Category> list = service.findAll();
//			//将返回的集合放入request
//			request.setAttribute("allCats", list);
//			//转发到真实的首页
//			return "/jsp/index.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return super.execute(request, response);
//	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//调用业务层查询最新商品，查询最热商品，返回两个集合
			ProductService service = new ProductServiceImpl();
			List<Product> list01 = service.findHots();
			List<Product> list02 = service.findNews();
			//将两个集合放入request
			request.setAttribute("hots", list01);
			request.setAttribute("news", list02);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//转到真实首页
		return "/jsp/index.jsp";
	}

}
