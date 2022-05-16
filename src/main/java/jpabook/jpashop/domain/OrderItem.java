package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    private int orderPrice; //주문가격
    private int count;//수량


    public void cancel(){
        this.item.addStock(count);
    }

    public int getTotalPrice(){
        return orderPrice*count;
    }
    public static OrderItem createOrderItem(Item item,int price , int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(price);
        item.removeStock(count);
        orderItem.setCount(count);
        return orderItem;
    }

}
