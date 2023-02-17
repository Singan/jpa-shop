package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.etc.AlbumForm;
import jpabook.jpashop.etc.BookForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
    private String etc;
    public void setAlbumForm(AlbumForm albumForm){
        this.setName(albumForm.getName());
        this.setPrice(albumForm.getPrice());
        this.setStock(albumForm.getStockQuantity());
        this.setArtist(albumForm.getArtist());
        this.setEtc(albumForm.getEtc());
    }
}
