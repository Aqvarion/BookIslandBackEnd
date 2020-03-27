package blackapple.webbook.productPart.controller;

import blackapple.webbook.model.CartItem;
import blackapple.webbook.productPart.entities.Book;
import blackapple.webbook.productPart.entities.Order;
import blackapple.webbook.productPart.entities.PurchaseRequest;
import blackapple.webbook.productPart.repository.BookRepository;import blackapple.webbook.productPart.repository.OrderRepository;
import blackapple.webbook.productPart.service.impl.OrderServiceImpl;
import blackapple.webbook.userPart.models.User;
import blackapple.webbook.userPart.payload.MessageResponse;
import blackapple.webbook.userPart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;import java.util.ArrayList;import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/buyItems")
    public ResponseEntity buyBooks(@RequestBody PurchaseRequest purchase) {

//        System.out.println(purchase);
//        for(Map.Entry<Integer,Integer> purchaseBook : purchase.getPurchasedBooks().entrySet()){
//            System.out.println("Key: "+purchaseBook.getKey()+" Value: "+purchaseBook.getValue());
//            System.out.println(purchaseBook.getKey().getClass().getName());
//            System.out.println(bookRepository.findById(1).get());
//            System.out.println(bookRepository.findById(purchaseBook.getKey()));
//            System.out.println(bookRepository.findById(purchaseBook.getKey()));
//        }
        List<CartItem> items = purchase.getPurchasedBooks().entrySet().stream()
                .map(purchasedBook -> new CartItem(bookRepository.findById(purchasedBook.getKey()).get(),purchasedBook.getValue()))
                .collect(Collectors.toList());
        User user = userRepository.findById(purchase.getUserId()).get();
        Order order = orderService.createOrder(user, items);
        orderRepository.save(order);
        return ResponseEntity.ok(new MessageResponse("Purchase successfully"));
    }
}
