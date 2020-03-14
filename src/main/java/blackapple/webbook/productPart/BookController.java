package blackapple.webbook.productPart;

import blackapple.webbook.productPart.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value = "/api/book")
public class BookController {


    @Autowired
    private BookRepository bookRepository;

    // method which gives all books
    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    // method which gives a selected book
    @GetMapping("get/{id}")
    public Optional<Book> getSelectedBook(@PathVariable int id){
        Optional<Book> book = bookRepository.findById(id);
        return book;
    }

}
