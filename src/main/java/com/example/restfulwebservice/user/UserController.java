package com.example.restfulwebservice.user;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController  //  @ResponseBody 디폴트 적용됨
public class UserController {
  //DI 의존성 주입, 스프링이 관리하는 인스턴스 = bean 이라고 부름
  //Dependency Injection
  //servlet contaioner, ioc container inversion of controller
  private UserDaoService service;

  public UserController(UserDaoService service){
      this.service = service;
  }

  @GetMapping("/users")
  public List<User> retrieveAllUsers(){
    return service.findAll();
  }

  //GET /users/1 or /users/10 -> String 으로 서버에 전달됨 다시 int 파라미터로 받으면 int로 받아짐
  @GetMapping("/users/{id}")
  public User retrieveUser(@PathVariable int id){
    User user = service.findOne(id);
    if(user == null) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }
    return user;
  }

  @PostMapping("/users")
  public ResponseEntity createSave(@Validated @RequestBody User u){
     User savedUser = service.save(u);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId())
        .toUri();

    return ResponseEntity.created(location).build();

  }

  @PutMapping("/users")
  public ResponseEntity updateUser(@RequestBody User u){
    log.info("controller ==>> u.getId = {}", u.getId());

    User updateUser = service.updateById(u);
    if(updateUser == null){
      throw new UserNotFoundException(String.format("ID[%s] not found", u.getId()));
    }
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(updateUser.getId())
        .toUri();

    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable int id){
    User user = service.deleteById(id);
    if(user == null){
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }
  }

}
