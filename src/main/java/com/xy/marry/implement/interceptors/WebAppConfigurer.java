package com.xy.marry.implement.interceptors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * 注册拦截器
 */
@Configuration // 使用注解 实现拦截
public class WebAppConfigurer implements WebMvcConfigurer {


    //关键，将拦截器作为bean写入配置中
    @Bean
    public InterceptorQjxcxAPI interceptorQjxcxAPI(){
        return new InterceptorQjxcxAPI();
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 登录拦截的管理器
		InterceptorRegistration registration = registry.addInterceptor(interceptorQjxcxAPI()); // 拦截的对象会进入这个类中进行判断
		registration.addPathPatterns("/api/project/qjxcx/**"); // api/project/qjxcx路径都被拦截
		registration.excludePathPatterns("/login", "/error", "/static/**", "/logout", "/swagger-ui.html"); // 添加不拦截路径

	}

}