/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.solutionapp.solutionapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author macbookpro
 */
@RestController
public class AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);
    
    protected ResponseEntity convertToJson(HashMap<String,Object> object, HttpStatus statusCode)
    {
        ResponseEntity res = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            String body = mapper.writeValueAsString(object);
            res = ResponseEntity.status(statusCode).body(body);
        }
        catch(Exception e)
        {
            LOG.error(e.getMessage());
            String mensagem = messageSource.getMessage("message.error.generic", null, Locale.getDefault());
            String body = String.format("%s: %s", mensagem, e.getMessage());
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
        
        return res;
    }
    
    
@Autowired
private MessageSource messageSource;
    
}
