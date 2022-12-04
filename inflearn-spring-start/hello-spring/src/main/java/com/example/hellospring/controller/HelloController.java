package com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        // 여기서의 model은 view model을 의미할 것
        model.addAttribute("data", "hello!!");

        // viewResolver를 통해 resources/templates/hello.html 로 매핑해 렌더링!
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);

        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // body에 return한 내용을 직접 넣어줄게~~!!
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // 객체 반환시에는 기본적으로 JSON (
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // cmd + shift + enter 하면 ; 같은거 자동으로 넣ㅇ줌
        hello.setName(name);

        // 객체라면 HttpMessageConverter 를 통해 처리 됨 (JSON or String...)
        // - StringHttpMessageConverter
        // - MappingJackson2HttpMessageConverter (JSON 라이브러리 -> GSON, Jackson 이 유명)
        return hello;
    }

    class Hello {
        private String name;

        // ctrl + enter 쓰면 자동 완성 뜸.
        // getter, setter 만드는 것은 java bean 표준 방식이라고 함

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
