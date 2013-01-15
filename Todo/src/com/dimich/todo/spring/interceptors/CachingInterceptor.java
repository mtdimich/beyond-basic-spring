package com.dimich.todo.spring.interceptors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CachingInterceptor extends HandlerInterceptorAdapter {

	private Map<String, String> requestCache = Collections.synchronizedMap(new HashMap<String, String>());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		System.out.println("CachingInterceptor.preHandle() - " + request.getRequestURI() + "-" + request.getMethod());
		if (isResourceCached(request.getRequestURI() + "-" + request.getMethod())) {
			response.setStatus(HttpStatus.NOT_MODIFIED.value());
			return false;
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("CachingInterceptor.afterCompletion()");
		super.afterCompletion(request, response, handler, ex);
	}

	private boolean isResourceCached(String requestURI) {

		return requestCache.containsKey(requestURI);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("CachingInterceptor.postHandle()");
		requestCache.put(request.getRequestURI() + "-" + request.getMethod(), "expiration");
	}

}
