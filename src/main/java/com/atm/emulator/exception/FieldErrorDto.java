package com.atm.emulator.exception;

public class FieldErrorDto {
    public FieldErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    String field;
    String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
