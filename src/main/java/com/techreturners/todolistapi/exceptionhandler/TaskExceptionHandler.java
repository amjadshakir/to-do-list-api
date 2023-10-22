package com.techreturners.todolistapi.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.techreturners.todolistapi.model.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class TaskExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ValidationError> validationErrors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ValidationError validationError = new ValidationError(error.getField(), error.getDefaultMessage());
                   validationErrors.add(validationError);
                });
        return validationErrors;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ValidationError handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        ValidationError validationError = new ValidationError();
        if (!(exception.getRootCause() instanceof InvalidFormatException invalidFormatException)) {
            return validationError;
        }
        if ("completed".equalsIgnoreCase(invalidFormatException.getPath().get(0).getFieldName())){
            validationError.setField("completed");
            validationError.setErrorMessage("Completed can either be true or false");
        }

        return validationError;
    }
}
