package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService = new MemberService();

    // 실제 상황에서는 memberService에서 repository를 주입받도록 하는게 좋겠다.
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given (상황)
        Member member = new Member();
        member.setName("hello");

        // when (이렇게 했을 때)
        Long saveId = memberService.join(member);


        // then (검증)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복_회원_에외() {
        // given (상황)
        Member member = new Member();
        member.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        // when (이렇게 했을 때)
        Long saveId = memberService.join(member);

        try {
            memberService.join(member2);
            Assertions.fail();
        } catch (IllegalStateException e) {
            Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
        }

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> memberService.join(member2)
        );


        // then (검증)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}