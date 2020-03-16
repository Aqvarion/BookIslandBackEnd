package blackapple.webbook.productPart.models;

import javax.persistence.*;
import java.sql.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "books_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books = new HashSet<>();

    @JoinTable(name = "books_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "quantity")
    )
    private Set<Integer> quantity = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "books_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = {}
    )
    @MapKey(name = "order_id")
    private Map<Book,Integer> kk = new HashMap<Book,Integer>();

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    private double totalCost;

    public Order(int userId) {
        this.userId = userId;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", books=" + books +
                '}';
    }
}
