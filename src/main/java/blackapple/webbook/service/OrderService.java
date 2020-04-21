package blackapple.webbook.service;

import blackapple.webbook.models.CartItem;import blackapple.webbook.models.Order;import blackapple.webbook.models.OrderedBook;
import blackapple.webbook.models.userModel.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, List<CartItem> items);
    void fillOrderItems(List<CartItem> items, Order order);
    OrderedBook createOrderedProduct(Order order,CartItem item);
}
