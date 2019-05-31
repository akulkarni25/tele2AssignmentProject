package com.tele2.assignment.subscriptionapi.exception;

import java.util.Date;

public class CustomErrorType {
		//private String errorMessage;
	 	private Date timestamp;
	    private String message;
	    private String details;
	 
	    public CustomErrorType(Date timestamp, String message, String details){
	    	super();
	        this.timestamp = timestamp;
	        this.message = message;
	        this.details = details;
	    }
	 
	    public Date getTimestamp() {
	        return timestamp;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public String getDetails() {
	        return details;
	    }
	 
}
