package com.example.webstoreauthservice.exception;

public class DuplicateException extends RuntimeException {

  public DuplicateException(String message) {
    super(message);
  }
}