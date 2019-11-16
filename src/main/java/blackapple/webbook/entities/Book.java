package blackapple.webbook.entities;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="genre")
    private String genre;

    public Book(){
    }

    public Book(String title, String genre) {
        this.title = title;
        this.genre = genre;
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

    @Override
    public String toString() {
        return "{" +
                 id +
                 title + '\'' +
                 genre + '\'' +
                '}';
    }
}
