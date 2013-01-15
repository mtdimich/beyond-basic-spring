package com.dimich.todo;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfig.class, loader = AnnotationConfigContextLoader.class)
public class SpringIntegrationTest {

	@Before
	public void init() {
		System.out.println("Initializing Spring Integration Tests");
	}

}
