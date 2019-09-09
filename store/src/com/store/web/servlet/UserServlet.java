package com.store.web.servlet;

import com.store.domain.User;
import com.store.service.UserService;
import com.store.service.impl.UserServiceImpl;
import com.store.utils.MailUtils;
import com.store.utils.MyBeanUtils;
import com.store.utils.UUIDUtils;
import com.store.web.base.BaseServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

   
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}

	//userRegister
	public String userRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接收表单数据	
//		User user = new User();
//		MyBeanUtils.populate(user, request.getParameterMap());
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		System.out.println(user);
		
			
		try {
			//调用业务层完成注册功能
			UserService service = new UserServiceImpl();
			service.userRegist(user);
			
			//注册成功，向用户邮箱发送信息，跳转到提示页面
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "用户注册成功，请激活");
		} catch (Exception e) {
			//注册失败，跳转到提示页面
			request.setAttribute("msg", "用户注册失败，请重新注册");
		}
		return "jsp/info.jsp";
	}
	
	//active
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String code = request.getParameter("code");
		
		UserService service = new UserServiceImpl();
		int r = service.active(code);
		
		if(r>0) {
			request.setAttribute("msg", "用户激活成功,请登录");
			service.alterState(code);
			service.updateCode(code);
			return"jsp/login.jsp";
		}else {
			request.setAttribute("msg", "用户激活失败");
			return"jsp/info.jsp";
		}
	}
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	
	//login
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//获取login.jsp传来的用户账号密码
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		
		//调用业务层登录功能
		UserService service = new UserServiceImpl();
		
		try {
			User user01 = service.userLogin(user);
			//用户登录成功，将用户信息放入session中
			request.getSession().setAttribute("loginUser", user01);
			//重定向
			response.sendRedirect("/store/jsp/index.jsp");
			return null;
		} catch (Exception e) {
			//用户登录失败
			String msg = e.getMessage();
			System.out.println(msg);
			
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
	}
	
	//logOut
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//清除session
		request.getSession().invalidate();
		//重新定向到首页
		response.sendRedirect("/store/jsp/index.jsp");
		return null;
	}
}
