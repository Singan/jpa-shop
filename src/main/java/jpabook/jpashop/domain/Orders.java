package jpabook.jpashop.domain;

import jpabook.jpashop.domain.en.DeliveryStatus;
import jpabook.jpashop.domain.en.Orderstatus;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Delivery delivery;
    @Enumerated(EnumType.STRING)
    private Orderstatus orderstatus;

    private LocalDateTime ordersDate;

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void setOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrders(this);
    }

    public static Orders createOrder(Member member,Delivery delivery,OrderItem... orderItems){
        Orders order =new Orders();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.setOrderItem(orderItem);
        }
        order.setOrderstatus(Orderstatus.Order);
        order.setOrdersDate(LocalDateTime.now());
        return order;
    }

    public void cancel(){
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송 완료된 상품입니다.");
        }
        this.setOrderstatus(Orderstatus.Cancel);
        for (OrderItem oi:this.orderItems) {
            oi.cancel();
        }
    }
    // 토탈 가격
    public int getPrice(){
        int price = 0;
        for (OrderItem oi:this.orderItems) {
            price += oi.getTotalPrice();
        }
        return price;
    }
}
