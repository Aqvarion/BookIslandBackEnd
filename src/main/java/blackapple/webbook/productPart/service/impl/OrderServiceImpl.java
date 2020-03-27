package blackapple.webbook.productPart.service.impl;

import blackapple.webbook.model.CartItem;import blackapple.webbook.productPart.entities.Book;import blackapple.webbook.productPart.entities.Order;import blackapple.webbook.productPart.entities.OrderedBook;import blackapple.webbook.productPart.service.OrderService;
import blackapple.webbook.userPart.models.User;
import org.springframework.stereotype.Service;import java.util.List;import java.util.Set;import static java.util.stream.Collectors.toSet;

@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public Order createOrder(User user, List<CartItem> items){
        Order order = new Order();
        order.setUser(user);
        fillOrderItems(items,order);
        double totalCost = items.stream().mapToDouble(item -> item.getQuantity() * item.getBook().getPrice()).sum();
        order.setTotalCost(totalCost);
        return order;
    }

    @Override
    public void fillOrderItems(List<CartItem> items, Order order) {
        Set<OrderedBook> ordered = items.stream()
                .map(item -> createOrderedProduct(order,item))
                .collect(toSet());
        order.setOrderedBooks(ordered);
    }

    @Override
    public OrderedBook createOrderedProduct(Order order,CartItem item) {
        OrderedBook orderedBook = new OrderedBook();
        orderedBook.setBook(item.getBook());
        orderedBook.setOrder(order);
        orderedBook.setQuantity(item.getQuantity());
        return orderedBook;
    }
}
