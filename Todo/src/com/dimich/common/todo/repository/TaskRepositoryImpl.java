package com.dimich.common.todo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.dimich.common.todo.business.Task;
import com.dimich.common.todo.business.TaskBuilder;
import com.dimich.common.todo.exception.TaskNotFoundException;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

	Map<String, Task> taskMap = new HashMap<String, Task>();

	@PostConstruct
	public void init() {
		String id1 = generateId();
		taskMap.put(
				id1,
				new TaskBuilder().withUserId("mtdimich").withTitle("Take out the garbage")
						.withDescription("Remember the recycling too.").withTaskId(id1).build());

		String id2 = generateId();
		taskMap.put(id2,
				new TaskBuilder().withUserId("mtdimich").withTitle("Get the mail").withDescription("Ignore the bills")
						.withTaskId(id2).build());

		String id3 = generateId();
		taskMap.put(id3, new TaskBuilder().withUserId("mtdimich").withTitle("Pick up Bread")
				.withDescription("2 loaves").withTaskId(id3).build());

		String id4 = generateId();
		taskMap.put(id4,
				new TaskBuilder().withUserId("mtdimich").withTitle("Go to the gym").withDescription("Pump iron")
						.withTaskId(id4).build());

		String id5 = generateId();
		taskMap.put(id5, new TaskBuilder().withUserId("mtdimich").withTitle("Send a birthday card").withDescription("")
				.withTaskId(id5).build());

		String id6 = generateId();
		taskMap.put(id6, new TaskBuilder().withUserId("sam").withTitle("Learn Scala").withDescription("Whohoo!")
				.withTaskId(id6).build());

		String id7 = generateId();
		taskMap.put(
				id7,
				new TaskBuilder().withUserId("sam").withTitle("Finish my website")
						.withDescription("You can get to this eventually").withTaskId(id7).build());

		String id8 = generateId();
		taskMap.put(id8, new TaskBuilder().withUserId("sam").withTitle("Get Milk").withDescription("Chocolate!")
				.withTaskId(id8).build());

		String id9 = generateId();
		taskMap.put(
				id9,
				new TaskBuilder().withUserId("sam").withTitle("Get Anniversary Present")
						.withDescription("Try to remember this time.").withTaskId(id9).build());
	}

	@Override
	public List<Task> getAllTasks() {
		return new ArrayList<Task>(taskMap.values());
	}

	@Override
	public List<Task> getAllTasksForUser(String userId) {

		List<Task> taskList = new ArrayList<Task>();

		Iterator<Task> it = taskMap.values().iterator();
		while (it.hasNext()) {
			Task task = it.next();

			if (task.getUserId().equals(userId)) {
				taskList.add(task);
			}
		}

		return taskList;
	}

	@Override
	public Task getTask(String taskId) {

		Task task = taskMap.get(taskId);

		if (null == task) {
			throw new TaskNotFoundException("Task with id " + taskId + " not found.");
		}

		return task;
	}

	@Override
	public void createTask(Task task) {
		String id = generateId();
		task.setTaskId(id);
		taskMap.put(id, task);
	}

	private String generateId() {
		return UUID.randomUUID().toString();
	}

}
