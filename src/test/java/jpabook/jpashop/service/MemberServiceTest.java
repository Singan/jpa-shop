package jpabook.jpashop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class) // 스프링 부트 테스트에 필요
@SpringBootTest // 스프링 부트를 띄우려면
@Transactional // 트랜잭션을 걸음 테스트에서는 롤백
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception{
        //given 이런게 주어졌을 때
        Member member = new Member();
        member.setUsername("멤1");
        member.setAddress(new Address("a","a","a"));
        // when 이렇게 하면
        Long id = memberService.join(member);
        //then 이렇게 되야한다.

        assertEquals(member,memberService.find(id));
    }

    @Test()
    public void 중복_회원_예외() throws Exception{
        Member member = new Member();
        member.setUsername("멤1");
        Member member2 = new Member();
        member2.setUsername("멤1");


        Long id1 = memberService.join(member);
        Long id2 = memberService.join(member);

        fail("예외 발생");

    }

    @Test
    public void 조회() throws Exception{
        List<Member> l = memberService.findAll();
        for (Member m:l) {
            System.out.println(m.getId());
            System.out.println(m.getUsername());
        }
    }
}