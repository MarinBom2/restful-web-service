package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.net.URI;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RequestMapping("/admin")
@RestController  //  @ResponseBody 디폴트 적용됨
public class AdminUserController {
  private UserDaoService service;

  public AdminUserController(UserDaoService service){
      this.service = service;
  }
  // GET /admin/users/1 -> /admin/v1/users/1
  @GetMapping("/users")
  public MappingJacksonValue retrieveAllUsers(){

    List<User> users = service.findAll();

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .filterOutAllExcept("id","name","joinDate", "password");

    FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
    MappingJacksonValue mapping = new MappingJacksonValue(users);
    mapping.setFilters(filters);

    return mapping;

  }

  //GET /users/1 or /users/10 -> String 으로 서버에 전달됨 다시 int 파라미터로 받으면 int로 받아짐
  @GetMapping("/v1/users/{id}")
  public MappingJacksonValue retrieveUserV1(@PathVariable int id){
    User user = service.findOne(id);

    if(user == null) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .filterOutAllExcept("id","name","joinDate", "password", "ssn");

    FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
    MappingJacksonValue mapping = new MappingJacksonValue(user);
    mapping.setFilters(filters);

    return mapping;
  }


  @GetMapping("/v2/users/{id}")
  public MappingJacksonValue retrieveUserV2(@PathVariable int id){
    User user = service.findOne(id);

    if(user == null) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .filterOutAllExcept("id","name","joinDate", "password", "ssn");

    FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
    MappingJacksonValue mapping = new MappingJacksonValue(user);
    mapping.setFilters(filters);

    return mapping;
  }


}
