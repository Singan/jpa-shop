package jpabook.jpashop.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class) // 스프링 부트 테스트에 필요
@SpringBootTest // 스프링 부트를 띄우려면
@Transactional // 트랜잭션을 걸음 테스트에서는 롤백
class ItemServiceTest {
    @Autowired
    private ItemService itemService;
    @Test
    void save() {
    }

    @Test
    void findAll() {
    }

    @Test
    void find() {
    }
}