package blackapple.webbook.productPart;

import blackapple.webbook.productPart.Book;
import blackapple.webbook.productPart.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class BookController {


    @Autowired
    BookRepository bookRepository;

    // method which gives all books
    @GetMapping("/books")
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    // method which gives a book`s list requested genre
    @GetMapping("/genres")
    public Set<String> getGenres(){
        Set<String> genres = new HashSet<>();
        bookRepository.findAll().forEach(book->{genres.add(book.getGenre());});
        return genres;
    }

    // method which gives a list of genres
    @GetMapping("{genre}")
    public List<Book> getBooksGenre(@PathVariable String genre){
        List<Book> books = getBooks();
        List<Book> booksGenre=new LinkedList<>();
        books.stream().filter(book -> book.getGenre().equals(genre)).forEach(booksGenre::add);
        return booksGenre;
    }

    // method which gives a selected book
    @GetMapping("get/{id}")
    public Optional<Book> getSelectedBook(@PathVariable int id){
        System.out.println("Get all books of this genre");
        Optional<Book> book = bookRepository.findById(id);
        return book;
    }

}
