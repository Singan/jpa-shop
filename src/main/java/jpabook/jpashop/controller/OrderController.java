package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Orders;
import jpabook.jpashop.etc.OrdersSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/orders")
    public String orderList(Model model,@ModelAttribute(name = "orderSearch") OrdersSearch orderSearch) {
/*        List<Orders> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);*/
        List<Orders> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/order")
    public String order(Long memberId, Long itemId, int count, Model model) {
        orderService.order(memberId, itemId, count);
        return "redirect:/";
    }

    @GetMapping("/order")
    public String orderForm(Model model) {
        model.addAttribute("members",memberService.findAll());
        model.addAttribute("items",itemService.findAll());

        return "order/orderForm";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String orderCancel(Model model, @PathVariable Long orderId) {

        orderService.cancelOrder(orderId);
        return "redirect:/";
    }
}
