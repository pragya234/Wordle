package com.example.Wordle.exception;

public class WordleException extends Exception{
    private int errorCode;
    public WordleException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
