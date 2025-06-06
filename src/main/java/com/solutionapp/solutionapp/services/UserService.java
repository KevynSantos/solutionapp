/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.solutionapp.solutionapp.services;

import com.solutionapp.solutionapp.dto.UserDto;
import com.solutionapp.solutionapp.models.User;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;

/**
 *
 * @author macbookpro
 */
public interface UserService {
    
    Pair<HttpStatus,HashMap<String,Object>> addOrUpdate(UserDto dto);
    
    List<User> findAll();

    public Pair<HttpStatus, HashMap<String, Object>> delete(Long id);
    
}
