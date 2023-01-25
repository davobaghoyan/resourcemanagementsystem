package com.exalt.resoursemanagementsystem.exception;

/**
 * The ErrorResponse class represents a response with a message and status code.
 */
public class ErrorResponse {
    public String errorMessage;
    public int errorCode;

    /**
     * This is constructor for ErrorResponse class, which creates new instances.
     * @param errorMessage
     * @param errorCode
     */
    public ErrorResponse(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
