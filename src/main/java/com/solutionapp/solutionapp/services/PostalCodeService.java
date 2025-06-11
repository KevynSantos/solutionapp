/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.solutionapp.solutionapp.services;

import java.util.HashMap;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;

/**
 *
 * @author macbookpro
 */
public interface PostalCodeService {
    
    Pair<HttpStatus,HashMap<String,Object>> get(String postalCode);
    
}
