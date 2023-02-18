package jpabook.jpashop.repository;

import jpabook.jpashop.domain.en.Orderstatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName; //회원 이름
    private Orderstatus orderStatus; //주문 상태[ORDER, CANCEL]
}
