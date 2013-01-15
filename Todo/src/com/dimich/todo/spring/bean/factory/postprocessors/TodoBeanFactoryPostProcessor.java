package com.dimich.todo.spring.bean.factory.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

//@Component
public class TodoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
		System.out.println("Running BeanFactoryPostProcessor");
		arg0.getBeansOfType(Object.class);

		for (String beanName : arg0.getBeanNamesForType(Object.class)) {
			System.out.println("Bean: " + beanName);
		}
		

	}

}
