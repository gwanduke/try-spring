package com.example.hellospring.controller;

import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @Controller라는 어노테이션이 있으며 스프링이 초기화되면서
// 이 클래스를 생성하고 들고있게 됨
@Controller
public class MemberController {
    private final MemberService memberService;


    // 스프링이 뜰 때 멤버서비스를 가져다가 연결해줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
