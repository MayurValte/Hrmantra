package com.hrmantra.attendanceDetails.genericResponse;

import org.springframework.http.HttpStatus;

public class GenericResponse <T>{
    private Integer status;
    private String message;
    private T payload;

    public GenericResponse(Integer status, String message, T payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public GenericResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public GenericResponse(Integer status) {
        this.status = status;
    }

    public GenericResponse(String message) {
        this.message = message;
    }

    public GenericResponse(T payload) {
        this.payload = payload;
    }

    public GenericResponse(Integer status, T payload) {
        this.status = status;
        this.payload = payload;
    }

    public GenericResponse(String message, T payload) {
        this.message = message;
        this.payload = payload;
    }

    public GenericResponse() {
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

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", payload=" + payload +
                '}';
    }
}
