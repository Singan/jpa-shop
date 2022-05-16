package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Orders;
import jpabook.jpashop.domain.en.Orderstatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private EntityManager em;
    @Test
    void order() {
        //given
        Member member = new Member();
        member.setUsername("석성희");
        member.setAddress(new Address("우리집","어디게","1231231"));
        em.persist(member);
        Book book = new Book();
        book.setName("지금 JPA");
        book.setPrice(10000);
        book.setStock(100); // 100권
        em.persist(book);
        em.flush();
        em.clear();
        //when
        Long orderId = orderService.order(member.getId(),book.getId(),30);
        Orders newOrder = orderService.find(orderId);

    }

    @Test
    void cancelOrder() {
        Member member = new Member();
        member.setUsername("석성희");
        member.setAddress(new Address("우리집","어디게","1231231"));
        em.persist(member);
        Book book = new Book();
        book.setName("지금 JPA");
        book.setPrice(10000);
        book.setStock(100); // 100권
        em.persist(book);
        em.flush();
        em.clear();
        Long orderId = orderService.order(member.getId(),book.getId(),30);
        Orders newOrder = orderService.find(orderId);
        //when
        orderService.cancelOrder(orderId);
        //then
        assertEquals(book.getStock(),100);
    }

    @Test
    void 재고초과(){
        //given
        Member member = new Member();
        member.setUsername("석성희");
        member.setAddress(new Address("우리집","어디게","1231231"));
        em.persist(member);
        Book book = new Book();
        book.setName("지금 JPA");
        book.setPrice(10000);
        book.setStock(100); // 100권
        em.persist(book);
        em.flush();
        em.clear();
        //when
        try{
            Long orderId = orderService.order(member.getId(), book.getId(), 101);
        }catch (NotEnoughException e){
            //then
            System.out.println("익센셥");
        }
        
        
    }
}