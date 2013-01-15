package com.dimich.todo.spring.bean.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class TodoBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		System.out.println("postProcessAfterInitialization: " + arg0.getClass() + "-" + arg1);
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		System.out.println("postProcessBeforeInitialization: " + arg0.getClass() + "-" + arg1);
		return arg0;
	}

}
