package com.solutionapp.solutionapp.controllers;

import com.solutionapp.solutionapp.controllers.AbstractController;
import com.solutionapp.solutionapp.dto.UserDto;
import com.solutionapp.solutionapp.models.User;
import com.solutionapp.solutionapp.services.UserService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")  
public class UserController extends AbstractController {

    @GetMapping("/findAll")  
    public ResponseEntity<?> findAll() {
        List<User> users = this.userService.findAll();
        HashMap<String, Object> status = new HashMap<>();
        status.put("list", users);
        status.put("message", users.isEmpty()?"Não há endereços disponiveis":null);
        return convertToJson(status, HttpStatus.OK);
    }
    
    @PutMapping("/addOrUpdate")
    public ResponseEntity<?> addOrUpdate(@RequestParam(value="id",required = false) Long id,@RequestBody UserDto userDto)
    {
        Pair<HttpStatus, HashMap<String, Object>> res = this.userService.addOrUpdate(userDto,id);
        
        return convertToJson(res.getSecond(), res.getFirst());
    } 
    
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(value="id") Long id)
    {
        Pair<HttpStatus, HashMap<String, Object>> res = this.userService.delete(id);
        
        return convertToJson(res.getSecond(), res.getFirst());
    }
    
    @GetMapping("findOne")
    public ResponseEntity<?> findOne(@RequestParam(value="id") Long id){
        Pair<HttpStatus, HashMap<String, Object>> res = this.userService.findOne(id);
        
        return convertToJson(res.getSecond(), res.getFirst());
    }
    
    @Autowired
    private UserService userService;
}
