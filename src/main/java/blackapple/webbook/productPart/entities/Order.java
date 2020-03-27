package blackapple.webbook.productPart.entities;

import blackapple.webbook.userPart.models.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE} , fetch = FetchType.EAGER, targetEntity = OrderedBook.class, mappedBy = "order")
    private Set<OrderedBook> orderedBooks = new HashSet<>();

    @Column(name = "total_cost")
    private double totalCost;

    public Order(User user) {
        this.user = user;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderedBook> getOrderedBooks() {
        return orderedBooks;
    }

    public void setOrderedBooks(Set<OrderedBook> orderedBooks) {
        this.orderedBooks = orderedBooks;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
