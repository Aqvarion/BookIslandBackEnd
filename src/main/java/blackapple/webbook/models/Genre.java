package blackapple.webbook.models;

import javax.persistence.*;

@Entity
@Table(name = "genre_t")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }
}
