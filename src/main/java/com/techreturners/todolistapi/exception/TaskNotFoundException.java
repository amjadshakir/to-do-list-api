package com.techreturners.todolistapi.exception;

public class TaskNotFoundException extends Throwable{
    public TaskNotFoundException(String message){
        super(message);
    }
}
