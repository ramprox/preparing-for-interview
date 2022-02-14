package ru.ramprox.rest;

import java.time.LocalDateTime;

public class ErrorDto {

    private final LocalDateTime timeStamp;

    private final String message;

    public ErrorDto(String message) {
        timeStamp = LocalDateTime.now();
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }
}
