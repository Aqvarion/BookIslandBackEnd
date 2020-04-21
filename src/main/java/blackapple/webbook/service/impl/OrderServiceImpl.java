package blackapple.webbook.service.impl;

import blackapple.webbook.models.CartItem;
import blackapple.webbook.models.Order;import blackapple.webbook.models.OrderedBook;import blackapple.webbook.service.OrderService;
import blackapple.webbook.models.userModel.User;
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
