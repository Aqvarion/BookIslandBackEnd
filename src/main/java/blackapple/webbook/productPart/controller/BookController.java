package blackapple.webbook.productPart.controller;

import blackapple.webbook.productPart.entities.Book;
import blackapple.webbook.productPart.entities.Order;
import blackapple.webbook.productPart.repository.BookRepository;
import blackapple.webbook.productPart.repository.OrderRepository;
import blackapple.webbook.userPart.payload.MessageResponse;
import blackapple.webbook.productPart.entities.PurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return bookRepository.findById(id);
    }
}
