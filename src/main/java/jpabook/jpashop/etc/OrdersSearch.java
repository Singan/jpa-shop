package jpabook.jpashop.etc;

import jpabook.jpashop.domain.en.Orderstatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersSearch {
    private Orderstatus orderstatus;
    private String userName;
}
