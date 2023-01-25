package com.exalt.resoursemanagementsystem.exception;

/**
 * The NotSufficientMemoryException class represents an exception,
 * which is thrown when there isn't sufficient memory
 */
public class NotSufficientMemoryException extends Exception {
    private static final String MESSAGE = "Not available free memory";

    /**
     * This is constructor for NotSufficientMemoryException class, which creates new instances.
     */
    public NotSufficientMemoryException() {
        super(MESSAGE);
    }
}
