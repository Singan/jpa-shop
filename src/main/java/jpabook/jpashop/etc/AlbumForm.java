package jpabook.jpashop.etc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumForm {
    private String name;
    private int price;
    private int stockQuantity;
    private String etc;
    private String artist;
}