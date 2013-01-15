package com.dimich.todo.spring;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dimich.todo.SpringIntegrationTest;
import com.dimich.todo.annotation.DeprecatedEndpoint;

public class SpringApplicationContextIntegrationTests extends SpringIntegrationTest {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void testNoDeprecatedEndpoints() {
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Controller.class);

		for (Object bean : beans.values()) {
			Class<?> beanClass = AopUtils.getTargetClass(bean); // HIGHLIGHT

			Method[] methods = beanClass.getMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(Deprecated.class)
						&& !method.isAnnotationPresent(DeprecatedEndpoint.class)) {
					fail(method + " uses wrong deprecation strategy; "
							+ "endpoints must use @DeprecatedEndpoint rather than @Deprecated");
				}
			}
		}
	}

	@Test
	public void testForRequestMappingAtTypeLevel() {
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Controller.class);

		for (Object bean : beans.values()) {
			Class<?> beanClass = AopUtils.getTargetClass(bean);

			assertFalse("@RequestMapping not supported at type level",
					beanClass.isAnnotationPresent(RequestMapping.class));
		}
	}

	@Test
	public void testMBeanVisibility() {
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ManagedResource.class);
		for (Object bean : beans.values()) {
			Class<?> beanClass = AopUtils.getTargetClass(bean);
			while (beanClass != null) {
				assertTrue("MBeans class not public: " + beanClass, Modifier.isPublic(beanClass.getModifiers()));
				beanClass = beanClass.getSuperclass();
			}
		}
	}
}
