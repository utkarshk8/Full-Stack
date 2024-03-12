package com.empbackend.employee.model;

public class Message {
    private String empName;
    private String message;
    private String email;

    public Message() {
    }

    public Message(String empName, String message, String email) {
        this.empName = empName;
        this.message = message;
        this.email = email;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
