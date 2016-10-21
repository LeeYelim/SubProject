package ex1012.filter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogSaveFilter implements Filter {

	String fileName;
	FileWriter fw;
	File file;
	@Override
	public void init(FilterConfig config) throws ServletException {
		fileName = config.getInitParameter("fileName");
		file = new File("C:/Edu/"+fileName+".txt");
		System.out.println("���� ����");
		try {
			fw = new FileWriter(file, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		fw.write("���� ��¥ : " + new Date(System.currentTimeMillis()) + "\n");
		fw.write("���� ip : " + req.getRemoteAddr() + "\n");
		fw.write("���� url : " + request.getRequestURL() + "\n");
		fw.flush();
		chain.doFilter(req, res);
	}
	
	@Override
	public void destroy() {
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
