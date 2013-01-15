package com.dimich.common.todo.service;

import java.util.List;

import com.dimich.common.todo.business.Task;

public interface TaskService {

	/**
	 * @param id
	 *            to retrieve
	 * @return individual task
	 */
	public Task getTask(String id);

	/**
	 * @return all tasks
	 */
	public List<Task> getTasks();

	/**
	 * @param userId
	 * @return All tasks for a specific user identifier
	 */
	public List<Task> getTasksForUser(String userId);

	/**
	 * Persist Task to the database
	 * 
	 * @param task
	 *            to be persisted
	 */
	public void createTask(Task task);
	
	public String getMysteryProperty();
}
