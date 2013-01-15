package com.dimich.common.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dimich.common.todo.business.Task;
import com.dimich.common.todo.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	@Value("${mystery.property}")
	private String mysteryProperty;

	@Override
	public Task getTask(String id) {

		Task task = taskRepository.getTask(id);

		return task;
	}

	@Override
	public List<Task> getTasks() {

		return taskRepository.getAllTasks();
	}

	@Override
	public List<Task> getTasksForUser(String userId) {

		return taskRepository.getAllTasksForUser(userId);
	}

	@Override
	public void createTask(Task task) {
		taskRepository.createTask(task);

	}

	@Override
	public String getMysteryProperty() {
		return mysteryProperty;
	}
	
	

}
