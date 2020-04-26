package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController{
    @GetMapping("/")
    public String sayHello(){
        return "Hello PAL...Chakradhar Keep Up the Good Work";
    }
}
