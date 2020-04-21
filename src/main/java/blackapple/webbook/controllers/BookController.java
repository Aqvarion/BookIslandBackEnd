package blackapple.webbook.controllers;

import blackapple.webbook.models.Book;
import blackapple.webbook.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value = "/api/book")
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //get all books
    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    //get a selected book
    @GetMapping("get/{id}")
    public Optional<Book> getSelectedBook(@PathVariable int id){
        return bookRepository.findById(id);
    }
}
