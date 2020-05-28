package blackapple.webbook.controllers;

import blackapple.webbook.models.Book;
import blackapple.webbook.models.Genre;
import blackapple.webbook.repositories.BookRepository;
import blackapple.webbook.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value = "/api/book")
public class BookController {

    private BookRepository bookRepository;
    private GenreRepository genreRepository;

    @Autowired
    public BookController(BookRepository bookRepository, GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
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

    @GetMapping("/genres")
    public Iterable<Genre> getGenres() {
        return genreRepository.findAll();
    }

}
