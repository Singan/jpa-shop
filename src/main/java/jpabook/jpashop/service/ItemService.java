package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    @Transactional
    public Long save(Item item){
        itemRepository.save(item);
        return item.getId();
    }


    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item find(Long id){
        return itemRepository.find(id);
    }
}
