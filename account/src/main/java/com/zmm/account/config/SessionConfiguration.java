package com.zmm.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.zmm.account.interceptor.AuthenticationInterceptor;
/**
 * 
 * @ClassName: SessionConfiguration
 * @Description: TODO(拦截器)
 * @author zhumingming
 * @date 2020年3月25日
 *
 */
@Configuration
public class SessionConfiguration extends WebMvcConfigurationSupport {
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthenticationInterceptor())
		           .addPathPatterns("/**")
				   .excludePathPatterns("/user/login")
				   .excludePathPatterns("/user/logOut")
				   .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
		super.addInterceptors(registry);
	}

	/**
	 * 放行swagger
	 */
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

	}

}
