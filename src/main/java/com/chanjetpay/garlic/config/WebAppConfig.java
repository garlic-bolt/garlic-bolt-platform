package com.chanjetpay.garlic.config;

import com.chanjetpay.garlic.common.ProfileInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Bean
	ProfileInterceptor profileInterceptor() {
		return new ProfileInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(profileInterceptor())
				.addPathPatterns("/home")
				.addPathPatterns("/vender/**")
				.excludePathPatterns("/index")
				.excludePathPatterns("/login")
				.excludePathPatterns("logoff");
		super.addInterceptors(registry);
	}
}
