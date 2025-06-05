package com.solutionapp.solutionapp.controller;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")  
public class AddressController extends AbstractController {

    @GetMapping("/test")  
    public ResponseEntity<?> getByCep() {
        HashMap<String, Object> status = new HashMap<>();
        status.put("status", true);
        return convertToJson(status, HttpStatus.OK);
    }
}
