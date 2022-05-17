package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.etc.BookForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;

    public void setBookForm(BookForm bookForm){
        this.setName(bookForm.getName());
        this.setPrice(bookForm.getPrice());
        this.setStock(bookForm.getStock());
        this.setAuthor(bookForm.getAuthor());
        this.setIsbn(bookForm.getIsbn());
    }
}
