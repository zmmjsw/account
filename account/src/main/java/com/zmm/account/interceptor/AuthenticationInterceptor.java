package com.zmm.account.interceptor;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.zmm.account.util.JwtUtils;

import lombok.extern.java.Log;

import com.zmm.account.annotation.Login;
import com.zmm.account.common.SubjectUtil;
import com.zmm.account.common.enums.ResponseStatusEnum;
import com.zmm.account.common.exception.MyException;
/**
 * 
 * @ClassName: AuthenticationInterceptor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhumingming
 * @date 2020年3月25日
 *
 */
@Log
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
	private static final String TOKEN="token";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) {
		log.info("************进入拦截器preHandle**********");
		// 如果不是映射到方法直接通过
		if (!(obj instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) obj;
		Method method = handlerMethod.getMethod();
		String name = method.getName();// 请求的方法
		if ("error".equals(name)) {//错误页直接返回。这里是放行swagger
			return true;
		}
		// 如果是登录接口
		if (method.isAnnotationPresent(Login.class)) {
			SubjectUtil.addSession(request,response);
			return true;
		}
		String token = request.getHeader(TOKEN);// 从 http 请求头中取出 token
		if (token == null) {
			throw new MyException(ResponseStatusEnum.NOT_LOGIN);
		}
		JwtUtils.verifyToken(token);//token校验
		PermissionCheck(token,request.getRequestURI());//权限校验
	

		return true;
	}
	

	private void PermissionCheck(String token,String requestURI) {
		Map<String, Claim> parseToken = JwtUtils.parseToken(token);// 解密
		String menus = parseToken.get(JwtUtils.PATHS).asString();
		List<String> menusList = JSON.parseArray(menus, String.class);
		if (!menusList.contains(requestURI)) {
			throw new MyException(ResponseStatusEnum.FORBIDDEN);
		}
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("************进入拦截器postHandle**********");
	}

	/**
	 * 在页面渲染完成返回给客户端之前执行
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exc,
			ModelAndView modelAndView) throws Exception {
		System.out.println("************进入拦截器afterCompletion**********");
	}

}
