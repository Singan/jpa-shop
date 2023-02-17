package jpabook.jpashop.domain;


import jpabook.jpashop.exception.NotEnoughException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    @Column(name = "item_name")
    private String name;
    private int price;
    private int stock;
    @ManyToMany(mappedBy = "items")
    private List<Category> categoryList = new ArrayList<>();

    public void addStock(int n){
        this.stock += n;
    }
    public void removeStock(int n){
        if(stock - n <0){
            throw new NotEnoughException("물량이 마이너스가 되어버립니다.");
        }
        this.stock -= n;
    }

}
