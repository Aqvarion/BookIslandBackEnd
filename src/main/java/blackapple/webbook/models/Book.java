package blackapple.webbook.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="title")
    private String title;

//    @ManyToOne
//    @JoinColumn(name = "genre")
    @Column(name = "genre")
    private String genre;

    @Column(name="author")
    private String author;

    @Column(name="price")
    private double price;

    @Column(name ="img")
    private String img;

    public Book(){
    }

    public Book(int id,String title, String genre, String author, double price, String img) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.price = price;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }
}
