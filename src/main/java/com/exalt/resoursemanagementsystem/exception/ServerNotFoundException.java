package com.exalt.resoursemanagementsystem.exception;

/**
 * The ServerNotFoundException class represents an exception,
 * which is thrown when server won't be found
 */
public class ServerNotFoundException extends Exception {
    private static final String MESSAGE = "Server with that id not found";

    /**
     * This is constructor for ServerNotFoundException class, which creates new instances.
     */
    public ServerNotFoundException() {
        super(MESSAGE);
    }
}
