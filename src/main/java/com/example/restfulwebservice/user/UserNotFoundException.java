package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// HTTP Status Code
// 2XX -> OK
// 4XX -> Client 가 존재하지 않거나 , 권한이없거나 하는 요청 클라이언트 요청 400번대 오류
// 5XX -> Server 측 Error
@ResponseStatus(HttpStatus.NOT_FOUND) //앞으로 이 예외 클래스는 서버측 오류중 데이터가 존재하지않는 오류라고 나옴
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
        super(message);
  }
}
