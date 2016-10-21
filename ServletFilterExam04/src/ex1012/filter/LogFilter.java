package ex1012.filter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "fileName", value = "C:/Edu/log.txt")
		})
public class LogFilter implements Filter {

	FileWriter fw;
	public void init(FilterConfig fConfig) throws ServletException {
		String fileName = fConfig.getInitParameter("fileName");
		try {
			fw = new FileWriter(fileName, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		StringBuilder sb = new StringBuilder();
		HttpServletRequest req = (HttpServletRequest)request;
		sb.append("立加 朝楼 : " + new Date().toLocaleString() +"\n");
		sb.append("立加 ip : " + request.getRemoteAddr()+"\n");
		sb.append("立加 uri : " + req.getRequestURI()+"\n");
		fw.write(sb.toString());
		fw.flush();
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		try {
			if(fw!=null) {
				fw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
