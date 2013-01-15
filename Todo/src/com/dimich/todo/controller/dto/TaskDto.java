package com.dimich.todo.controller.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import com.dimich.common.todo.business.Task;

public class TaskDto {

	@JsonProperty
	private Task task;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
