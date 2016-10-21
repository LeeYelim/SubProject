package ex1012.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	String encoding;
	@Override
	public void init(FilterConfig config) throws ServletException {
		//	init param받기
		encoding = config.getInitParameter("encoding");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filter)
			throws IOException, ServletException {
		// post방식 인코딩
		req.setCharacterEncoding(encoding);
		filter.doFilter(req, res);

	}

	@Override
	public void destroy() {

	}

	

}
