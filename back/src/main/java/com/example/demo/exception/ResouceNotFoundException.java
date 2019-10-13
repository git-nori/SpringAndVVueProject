package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND) // 例外の定義と特定の例外の応答ステータスを指定
public class ResouceNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ResouceNotFoundException(String message) {
        super(message);
    }
}
