package com.portfolio_backend.exception;

import com.portfolio_backend.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.file.FileAlreadyExistsException;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Response> handleMaxSizeException(MaxUploadSizeExceededException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Verifica el tamaño del archivo"));
    }
    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Ocurrio un problema"));
    }
    */


    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<Response> FileAlreadyExistsException(FileAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("El archivo ya se encuentra en el servidor"));
    }
}
