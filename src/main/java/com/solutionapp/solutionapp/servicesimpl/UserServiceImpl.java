/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.solutionapp.solutionapp.servicesimpl;
import com.solutionapp.solutionapp.dao.UserDao;
import com.solutionapp.solutionapp.dto.UserDto;
import com.solutionapp.solutionapp.models.User;
import com.solutionapp.solutionapp.services.UserService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author macbookpro
 */
@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    
    
    private User putUserData(User model, UserDto dto, boolean isUpdate)
    {
            model.setName(dto.getName());
            model.setCpf(dto.getCpf());
            model.setCity(dto.getCity());
            model.setNeighborhood(dto.getNeighborhood());
            model.setPostalCode(dto.getPostalCode());
            model.setState(dto.getState());
            model.setStreet(dto.getStreet());
            
            if(isUpdate)
            {
                model.setUpdatedAt(new Date());
            }
            else
            {
                model.setCreateAt(new Date());
            }
            
            model = this.userDao.saveAndFlush(model);
            
            return model;
    }

    @Override
    public Pair<HttpStatus,HashMap<String,Object>> addOrUpdate(UserDto dto,Long id) {
        
        HashMap<String,Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        
        try{
            User model;
            //se nao existir um id, entende-se que é um novo cadastro
            if(Objects.isNull(id))
            {
                model = new User();
                putUserData(model,dto,false);
                result.put("status", "sucesso");
                result.put("mensagem", null);
                return Pair.of(status, result);
                
            }
            Optional<User> user = this.userDao.findById(id);
            
            if(user.isPresent())
            {//se ja existir um usuario com esse id, faça a atualização
                model = user.get();
                putUserData(model,dto,true);
            }
            else
            {//se não existir um usuario com esse id, faça um novo cadastro
                model = new User();
                putUserData(model,dto,false);
            }
            
            result.put("status", "sucesso");
            result.put("mensagem", null);
        }
        catch(Exception e)
        {
          LOG.error(e.getMessage());
          String mensagem = messageSource.getMessage("message.error.generic", null, Locale.getDefault());
           String body = String.format("%s: %s", mensagem, e.getMessage());
           result.put("status", "erro");
           result.put("mensagem", body);
           status = HttpStatus.BAD_REQUEST;
        }
        
        return Pair.of(status, result);
        
    }
    
    @Autowired
    private MessageSource messageSource;

    @Override
    public List<User> findAll() {
        return this.userDao.findAll();
    }
    
   
    @Autowired
    private UserDao userDao;

    @Override
    public Pair<HttpStatus, HashMap<String, Object>> delete(Long id) {
        
        HashMap<String,Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try
        {
            this.userDao.deleteById(id);
            result.put("status", "sucesso");
            result.put("mensagem", null);
        }
        catch(Exception e)
        {
            LOG.error(e.getMessage());
           String mensagem = messageSource.getMessage("message.error.generic", null, Locale.getDefault());
           String body = String.format("%s: %s", mensagem, e.getMessage());
           result.put("status", "erro");
           result.put("mensagem", body);
           status = HttpStatus.BAD_REQUEST;
        }
        
        return Pair.of(status, result);
    }

    @Override
    public Pair<HttpStatus,HashMap<String,Object>> findOne(Long id) {
        HashMap<String,Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
       try
       {
           Optional<User> user = this.userDao.findById(id);
           result.put("status", "sucesso");
           if(user.isPresent())
           {
               result.put("user", user.get());
           }
           else
           {
               result.put("user", null);
               result.put("mensagem", "Endereço não encontrado");
           }
               
       }
       catch(Exception e)
       {
           LOG.error(e.getMessage());
           String mensagem = messageSource.getMessage("message.error.generic", null, Locale.getDefault());
           String body = String.format("%s: %s", mensagem, e.getMessage());
           result.put("status", "erro");
           result.put("mensagem", body);
           status = HttpStatus.BAD_REQUEST;
       }
       
       return Pair.of(status, result);
        
    }
}
