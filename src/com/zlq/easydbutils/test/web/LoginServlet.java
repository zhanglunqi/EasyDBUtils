package com.zlq.easydbutils.test.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zlq.easydbutils.factory.EasyFactory;
import com.zlq.easydbutils.test.service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -5537072074075602513L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserService us = EasyFactory.newServiceInstance(UserService.class);
		us.login(username,password);
	}

}
