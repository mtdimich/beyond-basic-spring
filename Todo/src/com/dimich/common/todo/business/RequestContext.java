package com.dimich.common.todo.business;

public class RequestContext {
	private String userIdentifier;
	private String name;

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name + "-" + this.userIdentifier;
	}

}
