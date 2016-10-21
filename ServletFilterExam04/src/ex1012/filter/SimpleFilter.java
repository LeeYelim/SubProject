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
		System.out.println("SimpleFilter의 init");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		// 사전처리
		System.out.println("sImpleFilter의 사전처리...");
		chain.doFilter(req, res); // 실제 대상인 웹컴포넌트 호출
		
		// 사후처리
		System.out.println("sImpleFilter의 사후처리...");
	}

	@Override
	public void destroy() {
		System.out.println("SimpleFilter의 destroy호출");
	}	

}
