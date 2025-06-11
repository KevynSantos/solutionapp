/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.solutionapp.solutionapp.controllers;

import com.solutionapp.solutionapp.services.PostalCodeService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author macbookpro
 */
@RestController
@RequestMapping("/address") 
public class PostalCodeController extends AbstractController {
    
    @GetMapping("/get") 
    public ResponseEntity<?> get(@RequestParam(value="postalCode") String postalCode)
    {
        Pair<HttpStatus,HashMap<String,Object>> res = this.postalCodeService.get(postalCode);
        return convertToJson(res.getSecond(), res.getFirst());
    }
    
    @Autowired
    private PostalCodeService postalCodeService;
    
}
