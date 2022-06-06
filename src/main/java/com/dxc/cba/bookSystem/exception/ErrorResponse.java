package com.dxc.cba.bookSystem.exception;

import java.util.Date;
import java.util.List;

/**
 * This class is the main error response class representing
 * response status, error message, and timestamp.
 * Usage of this class is to make sure the exception is conveyed to the user in a clear and concise way
 */
public class ErrorResponse {
    private int statusCode;
    private List<String> messages;
    private  Date timeStamp;

    public ErrorResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ErrorResponse(int statusCode, List<String> messages, Date timeStamp) {
        this.statusCode = statusCode;
        this.messages = messages;
        this.timeStamp = timeStamp;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
