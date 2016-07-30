package cn.cdahua.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class UpLoadFilter extends StrutsPrepareAndExecuteFilter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// 不过滤的url
		String url = req.getRequestURI();
		if ("/lsqn/UEditor/jsp/controller.jsp".equals(url)) {
			// 其他 /jsp/ 下的几个路径我不常用，就没写了
			//System.out.println("使用自定义的过滤器"+url);
			chain.doFilter(request, response);
		} else {
			//System.out.println("使用默认的过滤器");
			super.doFilter(request, response, chain);
		}
	}

}
