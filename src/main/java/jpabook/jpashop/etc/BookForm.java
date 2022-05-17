package jpabook.jpashop.etc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {
    private String name;
    private int price;
    private int stock;
    private String author;
    private String isbn;
    private Long id;
}