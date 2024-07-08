package org.root.src.users.infraestructura.models;

public class ResponseError {
    private final String message;
    private final boolean success = false;

    public ResponseError(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
