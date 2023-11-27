package com.hundsun.isms.etl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApp {


    @RequestMapping("/hello")
    public void hello() {
        System.out.println("hello native");
    }


}
