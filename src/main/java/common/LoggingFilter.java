package common;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class LoggingFilter extends HttpFilter implements Filter {
    PrintWriter writer;   
	
    public LoggingFilter() {
        super();
    }

	public void destroy() {
		writer.close();

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//요청시간
		long begin = System.currentTimeMillis();
		
		
		String path = ((HttpServletRequest)request).getContextPath();
		String uri = ((HttpServletRequest)request).getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/") +1);
		writer.printf("path:%s, uri:%s, cmd:%s \n", path,uri,cmd);
		
		//접근시간도 남기는게 기본이다(현재시간)
		GregorianCalendar now = new GregorianCalendar();
		writer.printf("접근시간:%TF, %TT %n", now, now);
		
		//서버주소도 저장을 해보자! (클라이언트 아이피주소)
		String addr = request.getRemoteAddr();
		writer.printf("접근주소:%s\n", addr);
		
		//클라이언트 포드!
		int port = request.getRemotePort();
		writer.printf("접근포트:%d\n", port);
		
		
		chain.doFilter(request, response);
		//응답시간
		long end = System.currentTimeMillis();
		writer.printf("응답시간:%d ms \n", (end-begin));
	}

	public void init(FilterConfig fConfig) throws ServletException {
		GregorianCalendar cal = new GregorianCalendar();
		String filename = cal.get(Calendar.YEAR) + "_" + (cal.get(Calendar.MONTH)+1) 
				+ "_" + cal.get(Calendar.DATE); //파일이름을 날짜로 하겠다
		
		try {
			writer = new PrintWriter(new FileWriter("d:\\KCM\\log\\" + filename +".log", true), true); //PrintWriter 는 출력한 내용을 콘솔에 찍는게 아니고 파일에 저장하게 하게!
		} catch (IOException e) {
			System.out.println("로그 파일 생성 오류");
		}
		
	}

}

















