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
		// 현재 문서의 한글 인코딩 설정
		response.setContentType("text/html;charset=UTF-8");
		
		// 현재 문서에 출력을 위한 출력 스트림
		PrintWriter out = response.getWriter();
		out.println("<h1>이름 : " + request.getParameter("name") + "</h1>");
	}

}
