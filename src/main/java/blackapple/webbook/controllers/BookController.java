package blackapple.webbook.controllers;

import blackapple.webbook.entities.Book;
import blackapple.webbook.exceptions.NotFoundException;
import blackapple.webbook.repositories.BookRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

 //   private List<Book> books= new LinkedList<Book>;
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return (List<Book>) bookRepository.findAll();
    }

//    @GetMapping("{genre}")
//    public List<Book> getBook(@PathVariable String genre){
//        return books.stream()
//                .filter(book.genre->genre)
//                .findAny()
//                .orElseThrow(NotFoundException::new);
//    }
}
