package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(name = "member_name")
    private String username;
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    private Address address;
}
