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
		
		//실제 경로 가져오기
		String path = fConfig.getServletContext().getRealPath("/");
		file = new File(path, fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Properties 파일을 로딩
		pro = new Properties();
		try {
			pro.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 병렬처리 - 프로세스를 분산시켜 좀 더 빠르게 작업을 수행 할 수 있도록 한다.
	 * jdk 1.5 이상부터 병렬처리가 가능한 스레드 프레임웍이 추가되었음
	 * Executor - 기본스레드(스레드 실행에 관련된 메소드만 존재)
	 * ExecutorService - (스레드 실행 + 안전하게 종료할 수 있는 메소드 제공)
	 * ScheduledExecutorService - (
	 * */
	
	ExecutorService service = Executors.newSingleThreadExecutor(); // 하나의 스레드를 만들어 그 안에서 분산처리
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// uri 주소를 가져온다
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				// 주소(key)가 있는지 찾는다
				String value = pro.getProperty(uri);
				// 없다면 추가, 있으면 값 변경
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
		service.shutdown(); // 더 이상 스레드를 받지X, 기존에 실행되고 있는 스레드를 모두 실행 완료한 후 종료
	}
}
