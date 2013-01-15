package com.dimich.todo.jmx;

import static org.apache.commons.lang.SystemUtils.LINE_SEPARATOR;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.dimich.common.todo.service.TaskService;
import com.dimich.todo.spring.interceptors.CachingInterceptor;

@Component
@ManagedResource(objectName = "com.dimich.task:type=DataSummary,name=TaskSummary", description = "Provides operations on the tasks application")
public class TaskMBeanImpl implements TaskMBean {

	@Autowired
	TaskService taskService;

	@Autowired
	CachingInterceptor cacheInterceptor;

	@Override
	@ManagedOperation(description = "Retrieves tasks summary for the system.")
	public String retrieveTaskSummary() {
		Map<String, Integer> resultMap = new HashMap<String, Integer>();

		resultMap.put("Number of Tasks", taskService.getTasks().size());
		resultMap.put("mtdimich tasks", taskService.getTasksForUser("mtdimich").size());
		resultMap.put("sam's tasks", taskService.getTasksForUser("sam").size());

		return stringify(resultMap);
	}

	private String stringify(Map<String, Integer> map) {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			sb.append(String.format("%s: %d%s", entry.getKey(), entry.getValue(), LINE_SEPARATOR));
		}

		return sb.substring(0, sb.lastIndexOf(LINE_SEPARATOR));
	}

}
