package com.store.web.servlet;

import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.impl.ProductServiceImpl;
import com.store.domain.PageModel;
import com.store.utils.UUIDUtils;
import com.store.web.base.BaseServlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//服务端获取到pid
			String pid = request.getParameter("pid");
			//根据pid查询对应的商品信息,
			ProductService ProductService = new ProductServiceImpl();
			Product pro = ProductService.findProductByPid(pid);
			//将商品放入request,
			request.setAttribute("product", pro);
			String ranStr = UUIDUtils.getId();
			//在session存放一份随机字符串
			request.getSession().setAttribute("ranStr", ranStr);
			//System.out.println(ranStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//转发到product_info.jsp
		return "/jsp/product_info.jsp";
	}
	
	public String findProductsWithCidAndPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取cid,num
			String cid = request.getParameter("cid");
			int curNum = Integer.parseInt(request.getParameter("num"));
			//调用业务层功能:以分页的形势查询当前类别下的商品信息
			//返回PageModel对象（1_当前页商品信息 2_分页3_url）
			ProductService service = new ProductServiceImpl();
			PageModel pm = service.findProductsWithCidAndPage(cid, curNum);
			//将PageModel对象放入request
			request.setAttribute("page", pm);
			//转发到/jsp/product_list.jsp
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"/jsp/product_list.jsp";
	}
	

}
