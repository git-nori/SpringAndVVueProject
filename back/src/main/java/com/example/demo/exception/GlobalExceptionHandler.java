package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/*
 * @ControllerAdvice
 * Controller専用の特殊なメソッド
 * (@InitBainderメソッド、@ModelAttributeメソッド、@ExceptionHandlerメソッド)
 * を複数のController(@Controller/@RestControllerを付与したクラス)で共有できます。
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /*
     * ErrorDetailsで定義したエラー応答を返すために設定
     * グローバルな範囲で固有の例外を1箇所で処理をする
     */
    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<?> resouceNotFoundException(ResouceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
