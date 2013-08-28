package com.dimich.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dimich.common.todo.business.RequestContext;
import com.dimich.common.todo.business.Task;
import com.dimich.common.todo.service.TaskService;
import com.dimich.todo.annotation.DeprecatedEndpoint;
import com.dimich.todo.controller.converter.TaskConverter;
import com.dimich.todo.controller.dto.TaskDto;

@Controller
public class TaskController {

	@Autowired
	TaskService taskService;

	@Autowired
	TaskConverter converter;

	/**
	 * 1. LIST tasks - This endpoint retrieves a list of tasks.
	 * 
	 * @ResponseBody tells spring to return whatever object is returned from the method
	 * directly to the response stream (after serializing).
	 * 
	 ****Demonstrating the following Spring MVC Annotations****
	 * @Request Mapping
	 * @ResponseBody
	 */
	@RequestMapping(value = "v1/tasks", method = RequestMethod.GET)
	@ResponseBody
	public List<Task> listTasksV1() {
		return taskService.getTasks();
	}
	
	/**
	 * 2. LIST task with produces
	 * 
	 * Version 2 of the endpoint uses the produces attribute on @RequestMapping.  This corresponds with
	 * the input in the Accept header.  
	 * 
	 * @RequestMapping
	 * @produces - Accept Header
	 */
	@RequestMapping(value = "v2/tasks", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Task> listTasksV2() {
		return taskService.getTasks();
	}

	/**
	 * 3. CREATE task - Creates a task in the system.
	 * 
	 ****Demonstrating the following Spring MVC Annotations****
	 * @RequestMapping
	 * @Consumes - Content-Type
	 * @RequestBody
	 * @ResponseStatus - sets the http response status upon successful execution of this method.
	 */
	@RequestMapping(value = "v1/tasks", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void createTaskV1(@RequestBody Task task) {
		taskService.createTask(task);
	}

	/**
	 * 4. GET Task
	 * 
	 * @PathVariable
	 * 
	 * ** Warning on @PathVariable - if you compile without certain flags, you'll need to explicitly specify the variable name.
	 */
	@RequestMapping(value = "v1/tasks/{taskId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public TaskDto getTaskV1(@PathVariable("taskId") String taskId) {

		return converter.toDto(taskService.getTask(taskId));

	}

	/*
	 * DEMO 2
	 */

	/**
	 * 5. LIST tasks - HandlerArgumentResolver
	 * 
	 * RequestContext is populated by the RequestContextArgumentResolver
	 * 
	 * @HandlerArgumentResolver
	 * @RequestHeader
	 * 
	 */
	@RequestMapping(value = "v3/tasks", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Task> listTasksV3(RequestContext requestContext, @RequestHeader("name") String name) {
		System.out.println("Requested by: " + requestContext.getName() + "-" + requestContext.getUserIdentifier());
		System.out.println("This comes from the header: " + name);
		return taskService.getTasks();
	}

	/**
	 * 6. GET Task - Web MVC Annotations
	 * 
	 * @PathVariable - one application
	 */
	@RequestMapping(value = "v1/user/{userId}/tasks", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Task> getTasksForUser(@PathVariable("userId") String userId) {

		return taskService.getTasksForUser(userId);

	}
	
	/**
	 * 7. LIST tasks - This endpoint retrieves a list of tasks.
	 * 
	 * @ResponseBody tells spring to return whatever object is returned from the method
	 * directly to the response stream (after serializing).
	 * 
	 * @DeprecatedEndpoint is a custom annotation that has an interceptor which looks for it.  When
	 * it finds an endpoint with @DeprecatedEndpoint, it adds a header field to notify the caller that
	 * the endpoint is deprecated. This is an example of combining spring concepts of interceptors and 
	 * Custom annotations.
	 * 
	 ****Demonstrating the following Spring MVC Annotations****
	 * @Request Mapping
	 * @ResponseBody
	 */
//	@DeprecatedEndpoint(expiryVersion = "1-25-2013", replacement = "v5/tasks")
	@RequestMapping(value = "v4/tasks", method = RequestMethod.GET)
	@ResponseBody
	@Deprecated
	public List<Task> listTasksV4() {
		return taskService.getTasks();
	}

}
