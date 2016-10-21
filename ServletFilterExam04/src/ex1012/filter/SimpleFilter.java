package ex1012.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SimpleFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("SimpleFilter�� init");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		// ����ó��
		System.out.println("sImpleFilter�� ����ó��...");
		chain.doFilter(req, res); // ���� ����� ��������Ʈ ȣ��
		
		// ����ó��
		System.out.println("sImpleFilter�� ����ó��...");
	}

	@Override
	public void destroy() {
		System.out.println("SimpleFilter�� destroyȣ��");
	}	

}
