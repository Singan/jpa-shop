package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/members/new")
    public String join(Member member){
        memberService.join(member);
        return "";
    }
}
