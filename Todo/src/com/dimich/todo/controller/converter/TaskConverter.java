package com.dimich.todo.controller.converter;

import org.springframework.stereotype.Component;

import com.dimich.common.todo.business.Task;
import com.dimich.todo.controller.dto.TaskDto;

@Component
public class TaskConverter {
	
	public TaskDto toDto(Task task){
		TaskDto taskDto = new TaskDto();
		taskDto.setTask(task);
		
		return taskDto;
	}

}
