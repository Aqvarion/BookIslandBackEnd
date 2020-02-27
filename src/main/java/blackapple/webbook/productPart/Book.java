package blackapple.webbook.productPart;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="genre")
    private String genre;

    @Column(name="author")
    private String author;

    @Column(name="price")
    private String price;

    public Book(){
    }

    public Book(int id,String title, String genre,String author, String price) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
