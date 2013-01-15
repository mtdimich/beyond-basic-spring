package com.dimich.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value = "v1/test") // - if you uncomment this, the integration test will fail on purpose.
public class RequestMappingController {

	@Autowired
	TaskController controller;
}
