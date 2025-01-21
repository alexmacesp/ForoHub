package com.forohub.challenge.infra.errores;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("Error", "El url especificado no fue encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarError400(MethodArgumentNotValidException ex){
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(),
                error.getDefaultMessage());});
        return ResponseEntity.badRequest().body(errores);
    }



}
