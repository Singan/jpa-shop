package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.etc.BookForm;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setBookForm(form);
        itemService.save(book);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateForm(Model model, @PathVariable("itemId") Long itemId) {
        Book book = (Book) itemService.find(itemId);
        BookForm bookForm = new BookForm();

        bookForm.setName(book.getName());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setIsbn(book.getIsbn());
        bookForm.setPrice(book.getPrice());
        bookForm.setStock(book.getStock());
        bookForm.setId(book.getId());

        model.addAttribute("form", bookForm);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String update(Model model, Book item) {
        itemService.save(item);

        return "redirect:/items";
    }
}