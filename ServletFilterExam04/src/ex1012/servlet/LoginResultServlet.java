package ex1012.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginResultServlet
 */
//@WebServlet("/LoginResult")
public class LoginResultServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���� ������ �ѱ� ���ڵ� ����
		response.setContentType("text/html;charset=UTF-8");
		
		// ���� ������ ����� ���� ��� ��Ʈ��
		PrintWriter out = response.getWriter();
		out.println("<h1>�̸� : " + request.getParameter("name") + "</h1>");
	}

}
