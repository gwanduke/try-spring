package com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        // 여기서의 model은 view model을 의미할 것
        model.addAttribute("data", "hello!!");

        // viewResolver를 통해 resources/templates/hello.html 로 매핑해 렌더링!
        return "hello";
    }
}
