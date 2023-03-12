package com.example.restfulwebservice.helloworld;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController //@ResponseBody 가 들어있음 response를 body 에 넣겠다는 뜻 , 기존 스프링은 String return 시 해당 url 을 리턴
public class HelloWorldController {
  @Autowired //어노테이션을 통한 의존성 주입 , 스프링 프레임웍 등록된 빈을 자동으로 주입
  private MessageSource messageSource;
  //Repretational state transfer 리소스 상태변환
  // GET
  // hello-world(endPoint)
  // @RequestMapping(method = RequestMethod.GET, path = "/hello-world") 을 사용했었음 전에는
  @GetMapping("/hello-world")
  public String helloWorld(){
    return "Hello world";
  }

  @GetMapping("/hello-world-bean")
  public HelloWorldBean helloWorldBean(){
    return new HelloWorldBean("Hello World");
  }

  @GetMapping("/hello-world/path-variable/{name}") //overloading
  public HelloWorldBean helloWorldBean(@PathVariable String name){
    return new HelloWorldBean(String.format("Hello %s", name));
  }

  @GetMapping("/hello-world-internationalized")
  public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale){

      return messageSource.getMessage("greeting.message", null, locale);
  }


}
