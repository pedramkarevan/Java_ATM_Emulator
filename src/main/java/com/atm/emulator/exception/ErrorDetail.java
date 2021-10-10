package com.atm.emulator.exception;

import org.omg.CORBA.Any;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetail {
    public ErrorDetail(LocalDateTime timestamp, Integer status, String message, Any error) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.error = error;
    }

    private LocalDateTime timestamp;
  private Integer status;
  private String message;
  private Any error;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Any getError() {
        return error;
    }

    public void setError(Any error) {
        this.error = error;
    }
}
