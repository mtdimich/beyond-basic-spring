package com.dimich.common.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TaskSecurityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskSecurityException(String message) {
		super(message);
	}
}
