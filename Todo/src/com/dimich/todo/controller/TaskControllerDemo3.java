package com.dimich.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dimich.common.todo.service.TaskService;

@Controller
public class TaskControllerDemo3 {

	@Autowired
	TaskService taskService;

	@RequestMapping(value = "v1/mysteryProperty", method = RequestMethod.GET)
	@ResponseBody
	public String getMysteryProperty() {
		return taskService.getMysteryProperty();
	}
}
