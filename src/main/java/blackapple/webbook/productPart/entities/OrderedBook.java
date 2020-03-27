package blackapple.webbook.productPart.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ordered_product")
public class OrderedBook implements Serializable {

    @EmbeddedId
    private OrderedBookId pk = new OrderedBookId();

    @MapsId("orderId")
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @MapsId("productId")
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    @Column(name = "quantity")
    private int quantity;

    public OrderedBook() {
    }

    public Order getOrder() {
    return order;
    }

    public void setOrder(Order order) {
    this.order = order;
    }

    public Book getBook() {
    return book;
    }

    public void setBook(Book book) {
    this.book = book;
    }

    public int getQuantity() {
    return quantity;
    }

    public void setQuantity(int quantity) {
    this.quantity = quantity;
    }
}
