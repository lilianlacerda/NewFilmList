package com.liliSolution.filmListNew.listFilm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.liliSolution.filmListNew.listFilm.exception.MoovieNullException;
import com.liliSolution.filmListNew.listFilm.exception.MoovieTituloException;

@ControllerAdvice
public class FilmeControllerAdvice extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(MoovieNullException.class)
    public ResponseEntity<Object> captureErroNull(){

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", "Verifique se os campos obrigatórios foram preenchidos corretamente.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MoovieTituloException.class)
    public ResponseEntity<Object> Exception(){

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", "Este filme já existe na sua lista. Acho que está na hora de assistir ele, hein?");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
