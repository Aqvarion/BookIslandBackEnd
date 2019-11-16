package blackapple.webbook.controllers;

import blackapple.webbook.entities.Book;
import blackapple.webbook.exceptions.NotFoundException;
import blackapple.webbook.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/catalog")
public class BookController {

 //   private List<Book> books= new LinkedList<Book>;
    @Autowired
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // method which gives all books
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        System.out.println("Get all books");
        List<Book> books = new LinkedList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    // method which gives a book`s list requested genre
    @GetMapping("/genres")
    public Set<String> getGenres(){
        System.out.println("Get all genres");
        Set<String> genres = new HashSet<>();
        bookRepository.findAll().forEach(book->{genres.add(book.getGenre());});
        return genres;
    }

    // method which gives a list of genres
    @GetMapping("{genre}")
    public List<Book> getBooksGenre(@PathVariable String genre){
        System.out.println("Get all books of this genre");
        List<Book> books = getAllBooks();
        List<Book> booksGenre=new LinkedList<>();
        books.stream().filter(book -> book.getGenre().equals(genre)).forEach(booksGenre::add);
        return booksGenre;
    }

    // method which gives a selected book
    @GetMapping("get/{id}")
    public List<Book> getSelectedBook(@PathVariable int id){
        System.out.println("Get all books of this genre");
//        return getAllBooks().stream()
//                .filter(book -> (book.getId()==id))
//                .forEach(System.out::println(););
        List<Book> books = new LinkedList<>();
        bookRepository.findAll().forEach(books::add);
        List<Book> books2 = new LinkedList<>();
        books.stream().filter(book -> book.getId()==id).forEach(books2::add);
        return books2;


    }

//    @GetMapping("/test")
//    public void test(){
//        getAllBooks().forEach(book -> System.out.println(book.getId()));
//    }



}
