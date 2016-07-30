package cn.cdahua.filter;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.cdahua.exception.MyException;
import cn.cdahua.model.Admin;
import cn.cdahua.model.Author;
import cn.cdahua.util.ActionUtils;

@SuppressWarnings("serial")
@Component("authInterceptor")
public class AuthInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 1、获取action的名称
		String an = invocation.getProxy().getActionName();
		if (!an.startsWith("author_register") && !an.startsWith("author_index") && !an.startsWith("login") && !an.startsWith("admin_loginInput") && !an.startsWith("author_registerInput")) {
			Admin admin = (Admin) ActionContext.getContext().getSession().get("admin");
			Author author = (Author) ActionContext.getContext().getSession().get("author");

			if (admin == null && author == null)
				return "logininput";
			// 来进行其他权限控制
			if (admin == null) {
				// 普通用户
				if (!ActionUtils.checkUrl(an)) {
					throw new MyException("您的权限不足");
				}
			}
		}
		return invocation.invoke();
	}

}
