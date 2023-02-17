package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.en.DeliveryStatus;
import jpabook.jpashop.etc.OrdersSearch;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional// 주문
    public Long order(Long memberId,Long itemId , int count) {
        Member member = memberRepository.find(memberId);
        Item item = itemRepository.find(itemId);

        Delivery delivery = new Delivery();
        delivery.setDeliveryStatus(DeliveryStatus.READY);
        delivery.setAddress(member.getAddress());
        OrderItem oi = OrderItem.createOrderItem(item,item.getPrice(),count);
        return orderRepository.save(Orders.createOrder(member,delivery,oi));
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Orders orders = orderRepository.find(orderId);

        orders.cancel();
    }

    public Orders find(Long orderId){
        return orderRepository.find(orderId);

    }
    public List<Orders> findOrders(OrdersSearch orderSearch){

        return orderRepository.findByCriteria(orderSearch);
    }
    public List<Orders> findAll(){
        return orderRepository.findAll();

    }
}
