package blackapple.webbook.controllers;

import blackapple.webbook.models.CartItem;
import blackapple.webbook.models.Order;
import blackapple.webbook.models.PurchaseRequest;
import blackapple.webbook.repositories.BookRepository;
import blackapple.webbook.repositories.OrderRepository;
import blackapple.webbook.service.impl.OrderServiceImpl;
import blackapple.webbook.models.userModel.User;
import blackapple.webbook.payload.MessageResponse;
import blackapple.webbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/order")
public class OrderController {

    private OrderRepository orderRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderRepository orderRepository,
                           BookRepository bookRepository,
                           UserRepository userRepository,
                           OrderServiceImpl orderService) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @PostMapping("/buyItems")
    public ResponseEntity buyBooks(@RequestBody PurchaseRequest purchase) {

        List<CartItem> items = purchase.getPurchasedBooks().entrySet().stream()
                .map(purchasedBook -> new CartItem(bookRepository.findById(purchasedBook.getKey()).get(),purchasedBook.getValue()))
                .collect(Collectors.toList());
        User user = userRepository.findById(purchase.getUserId()).get();
        Order order = orderService.createOrder(user, items);
        orderRepository.save(order);
        return ResponseEntity.ok(new MessageResponse("Purchase successfully"));
    }
}
