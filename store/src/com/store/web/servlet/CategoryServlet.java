package com.store.web.servlet;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.utils.JedisUtils;
import com.store.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	
	//findAllCats
	public String findAllCats(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try {
			response.setContentType("application/json;charset=utf-8");
			String jsonStr="";
			Jedis j = JedisUtils.getJedis();
			jsonStr = j.get("allCats");
			if(null==jsonStr||"".equals(jsonStr)){
				//System.out.println("缓存中没有数据");
				//查询所有分类
				CategoryService Service=new CategoryServiceImpl();
				List<Category> list = Service.findAll();
				//将集合中的所有分类信息转换为JSON格式的字符串数据
				jsonStr=JSONArray.fromObject(list).toString();
				j.set("allCats", jsonStr);
				
			}else{
				//System.out.println("缓存中有数据");
			}
			//将字符串数据响应到客户端
			response.getWriter().println(jsonStr);
			JedisUtils.closeJedis(j);			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;

	}

    

}
