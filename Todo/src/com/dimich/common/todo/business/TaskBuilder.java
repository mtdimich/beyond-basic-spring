package com.dimich.common.todo.business;

public class TaskBuilder {

	private Task task;

	public TaskBuilder() {
		task = new Task();
	}

	public TaskBuilder withUserId(String userId) {
		task.setUserId(userId);
		return this;
	}

	public TaskBuilder withTitle(String title) {
		task.setTitle(title);
		return this;
	}

	public TaskBuilder withDescription(String description) {
		task.setDescription(description);
		return this;
	}

	public TaskBuilder withTaskId(String taskId) {
		task.setTaskId(taskId);
		return this;
	}

	public Task build() {
		return task;
	}

}
