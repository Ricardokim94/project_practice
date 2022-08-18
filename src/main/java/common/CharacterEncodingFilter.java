package common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;


@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "encoding", value = "utf-8")
		})
public class CharacterEncodingFilter extends HttpFilter implements Filter {
	
	FilterConfig config;
	
	
    public CharacterEncodingFilter() {
        super();
    }

	public void destroy() {
		System.out.println("인코딩 필터 종료 : destroy() 호출");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter() 호출");
		//요청필터기능
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		chain.doFilter(request, response); //서블릿으로 가려면 이걸 해야된다. (그다음 필터가 있는지 찾는다. 없으면 서블릿을 실행한다.)
		//응답필터기능
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
		System.out.println("인코딩 초기값설정 : init() 호출");
	}

}
