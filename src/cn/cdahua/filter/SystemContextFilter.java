package cn.cdahua.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.cdahua.model.SystemContext;

public class SystemContextFilter implements Filter {

	
	int pageSize = 0;
	/**
	 * Default constructor.
	 */
	public SystemContextFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8"); 
		try {
			int pageOffset = 0;
			try {
				pageOffset = Integer.parseInt(request.getParameter("pager.offset"));
			} catch (NumberFormatException e) {}
			SystemContext.setPageOffSet(pageOffset);
			SystemContext.setPageSize(pageSize);
			chain.doFilter(request, response);
		} finally {
			SystemContext.removePageOffset();
			SystemContext.removePageSize();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		try {
			pageSize = Integer.parseInt(fConfig.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			pageSize = 10;
		}
	}

}
