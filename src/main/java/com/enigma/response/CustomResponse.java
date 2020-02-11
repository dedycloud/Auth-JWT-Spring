package com.enigma.response;

public class CustomResponse<T> {
private String status;
private String messages;
private T data ;
    public CustomResponse() {
        this.status = "200";
        this.messages = "SUCCESS";
    }

    public CustomResponse(String status, String messages) {
        this.status = status;
        this.messages = messages;
    }
    public CustomResponse(T data) {
        this();
        this.data = data;
    }
    public CustomResponse(String status, String messages, T data) {
        this.status = status;
        this.messages = messages;
        this.data = data;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessages() {
        return messages;
    }
    public void setMessages(String messages) {
        this.messages = messages;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
