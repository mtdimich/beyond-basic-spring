package com.dimich.todo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.dimich", excludeFilters = { @Filter(Configuration.class) })
public class SpringTestConfig {

}
