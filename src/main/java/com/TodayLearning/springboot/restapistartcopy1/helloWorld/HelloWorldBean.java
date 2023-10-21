package com.TodayLearning.springboot.restapistartcopy1.helloWorld;

public class HelloWorldBean 
{
    private String message;

	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}

	

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
    
	
}
