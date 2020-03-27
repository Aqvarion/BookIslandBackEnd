package blackapple.webbook.productPart.service;

import blackapple.webbook.model.CartItem;import blackapple.webbook.productPart.entities.Order;import blackapple.webbook.productPart.entities.OrderedBook;
import blackapple.webbook.userPart.models.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, List<CartItem> items);
    void fillOrderItems(List<CartItem> items, Order order);
    OrderedBook createOrderedProduct(Order order,CartItem item);
}
