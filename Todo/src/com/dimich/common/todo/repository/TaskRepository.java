package com.dimich.common.todo.repository;

import java.util.List;

import com.dimich.common.todo.business.Task;

public interface TaskRepository {

	public List<Task> getAllTasks();

	public List<Task> getAllTasksForUser(String userId);

	public Task getTask(String taskId);

	public void createTask(Task task);

}
