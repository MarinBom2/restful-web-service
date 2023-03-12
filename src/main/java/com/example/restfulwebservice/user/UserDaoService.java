package com.example.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Instanceof;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDaoService { //인스턴스로 생성하여 service 라는 클래스를 메모리에 등록
  private static List<User> users = new ArrayList<>();
  private static int usersCount = 3;
  static {
    users.add(new User(1,"gojgho1", new Date(), "pass1", "701010-1111111"));
    users.add(new User(2,"gojgho2", new Date(),"pass2", "801010-1111111"));
    users.add(new User(3,"gojgho3", new Date(), "pass3","901010-1111111"));
  }

  public List<User> findAll(){
    return users;
  }

  public User save(User u){
    if(u.getId() == null){
      u.setId(++usersCount);
    }
    users.add(u);
    return u;
  }

  public User findOne(int id){
    for(User u : users){
      if(u.getId() == id){
        return u;
      }
    }
    return null;
  }

  public User updateById(User u) {
    for(User user : users){
      if(u.getId() == user.getId()){
        User updateUser = users.get(u.getId()-1);
        updateUser.setName(u.getName());
        updateUser.setJoinDate(u.getJoinDate());
        return updateUser;
      }
    }
    return null;
  }

  public User deleteById(int id) {
    // 열거형 data 값을 순차적으로 접근해서 사용하기위한 열거형타입 iterator
    Iterator<User> iterator = users.iterator();
    while(iterator.hasNext()){
      User user = iterator.next();
      if(user.getId() == id){
        iterator.remove();
        return user;
      }
    }
    return null;
  }



}
