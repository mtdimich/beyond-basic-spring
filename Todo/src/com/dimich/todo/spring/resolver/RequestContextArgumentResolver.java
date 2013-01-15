package com.dimich.todo.spring.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.dimich.common.todo.business.RequestContext;

@Component
public class RequestContextArgumentResolver implements
		HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer arg1, NativeWebRequest webRequest,
			WebDataBinderFactory arg3) throws Exception {

		RequestContext requestContext = new RequestContext();
		requestContext.setUserIdentifier(webRequest.getHeader("userId"));
		requestContext.setName(webRequest.getHeader("name"));
		
		return requestContext;

	}

	@Override
	public boolean supportsParameter(MethodParameter arg0) {

		if (arg0.getParameterType().equals(RequestContext.class)) {
			return true;
		}
		return false;
	}

}
