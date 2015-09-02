package com.zlq.easydbutils.test.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zlq.easydbutils.factory.EasyFactory;
import com.zlq.easydbutils.test.domain.User;
import com.zlq.easydbutils.test.service.UserService;

public class RegistServlet extends HttpServlet {

	private static final long serialVersionUID = -7358582224880624330L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		UserService us = EasyFactory.newServiceInstance(UserService.class);
		us.addUser(user);
	}

}
