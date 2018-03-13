package org.primejava.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primejava.cms.model.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("loginUser");
		if(user==null) {
			response.sendRedirect(request.getContextPath()+"/login/login");
		} 
		return super.preHandle(request, response, handler);
	}
}
