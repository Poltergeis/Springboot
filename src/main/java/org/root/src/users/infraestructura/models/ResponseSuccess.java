package org.root.src.users.infraestructura.models;

public class ResponseSuccess<T> {
    private final T data;
    private final boolean success = true;

    public ResponseSuccess(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }
}
