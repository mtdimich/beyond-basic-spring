package com.dimich.todo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dimich.todo.spring.interceptors.CachingInterceptor;
import com.dimich.todo.spring.interceptors.DeprecatedEndpointInterceptor;
import com.dimich.todo.spring.resolver.RequestContextArgumentResolver;

@Configuration
@EnableWebMvc
@WebAppConfiguration
public class SpringConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new RequestContextArgumentResolver());
		super.addArgumentResolvers(argumentResolvers);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DeprecatedEndpointInterceptor());
		registry.addInterceptor(new CachingInterceptor());
		super.addInterceptors(registry);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[] { new ClassPathResource("todo.properties") };
		pspc.setLocations(resources);
		pspc.setIgnoreUnresolvablePlaceholders(true);
		return pspc;
	}

}
