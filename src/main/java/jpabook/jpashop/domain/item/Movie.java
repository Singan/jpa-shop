package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.etc.BookForm;
import jpabook.jpashop.etc.MovieForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String actor;
    public void setMovieForm(MovieForm movieForm){
        this.setName(movieForm.getName());
        this.setPrice(movieForm.getPrice());
        this.setStock(movieForm.getStockQuantity());
        this.setDirector(movieForm.getDirector());
        this.setActor(movieForm.getActor());
    }
}
