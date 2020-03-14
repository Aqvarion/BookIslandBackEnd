package blackapple.webbook.productPart.models;

import javax.persistence.*;
import java.sql.Array;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
}
