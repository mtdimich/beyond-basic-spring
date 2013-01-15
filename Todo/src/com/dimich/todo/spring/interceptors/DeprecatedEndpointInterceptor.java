package com.dimich.todo.spring.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.apache.log4j.Logger;

import com.dimich.todo.annotation.DeprecatedEndpoint;

public class DeprecatedEndpointInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = Logger.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		System.out.println("DeprecatedEndpointInterceptor.preHandle()");
		if (isEndpointDeprecated(request, handler)) {
			response.addHeader("deprecated", "true");
			System.out.println("Set Response Header to deprecated");
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("DeprecatedEndpointInterceptor.postHandle()");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("DeprecatedEndpointInterceptor.afterCompletion()");
		super.afterCompletion(request, response, handler, ex);
	}

	private boolean isEndpointDeprecated(HttpServletRequest request, Object handler) {
		// Find Controller Method that was invoked
		// Spring 3.1+ provides direct resolution to a method in some cases
		if (handler.getClass().getSimpleName().equals("HandlerMethod")) {
			Method controllerMethod = findControllerMethod(handler);

			if (null != controllerMethod) {
				DeprecatedEndpoint deprecatedEndpoint = controllerMethod.getAnnotation(DeprecatedEndpoint.class);
				if (null != deprecatedEndpoint) {
					return true;
				}

			}
		}

		logger.warn("No controller method found for request URI " + request.getRequestURI() + ".");
		return false;
	}

	private Method findControllerMethod(final Object handlerMethod) {
		Method controllerMethod = null;

		// call HandlerMethod#getMethod reflectively to retain compatibility
		// with Spring 3.0
		try {
			final Class<?> clazz = Class.forName("org.springframework.web.method.HandlerMethod");
			final Method getMethod = clazz.getDeclaredMethod("getMethod");
			controllerMethod = (Method) getMethod.invoke(handlerMethod);
		} catch (final Exception exception) {
			logger.warn("Unable to call HandlerMethod#getMethod.", exception);
		}

		return controllerMethod;
	}

}
