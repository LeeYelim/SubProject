package ex1012.filter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
 * Servlet Filter implementation class ClickCountFilter
 */
@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "fileName", value = "clickCount.properties")
		})
public class ClickCountFilter implements Filter {

	Properties pro;
	File file;
	public void init(FilterConfig fConfig) throws ServletException {
		String fileName = fConfig.getInitParameter("fileName");
		
		//���� ��� ��������
		String path = fConfig.getServletContext().getRealPath("/");
		file = new File(path, fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Properties ������ �ε�
		pro = new Properties();
		try {
			pro.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����ó�� - ���μ����� �л���� �� �� ������ �۾��� ���� �� �� �ֵ��� �Ѵ�.
	 * jdk 1.5 �̻���� ����ó���� ������ ������ �����ӿ��� �߰��Ǿ���
	 * Executor - �⺻������(������ ���࿡ ���õ� �޼ҵ常 ����)
	 * ExecutorService - (������ ���� + �����ϰ� ������ �� �ִ� �޼ҵ� ����)
	 * ScheduledExecutorService - (
	 * */
	
	ExecutorService service = Executors.newSingleThreadExecutor(); // �ϳ��� �����带 ����� �� �ȿ��� �л�ó��
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// uri �ּҸ� �����´�
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				// �ּ�(key)�� �ִ��� ã�´�
				String value = pro.getProperty(uri);
				// ���ٸ� �߰�, ������ �� ����
				if(value==null) {
					pro.setProperty(uri, "1");
				} else {
					int count = Integer.parseInt(pro.getProperty(uri));
					count++;
					pro.setProperty(uri, ""+count);
				}
				try {
					pro.store(new FileWriter(file), "");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		service.shutdown(); // �� �̻� �����带 ����X, ������ ����ǰ� �ִ� �����带 ��� ���� �Ϸ��� �� ����
	}
}
