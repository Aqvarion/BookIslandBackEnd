package blackapple.webbook.productPart;

import blackapple.webbook.productPart.models.Book;
import blackapple.webbook.productPart.models.Order;
import blackapple.webbook.userPart.payload.MessageResponse;
import blackapple.webbook.userPart.payload.PurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value = "/api/book")
public class BookController {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

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

    @PostMapping("/buyItems")
    public ResponseEntity buyBooks(@RequestBody PurchaseRequest purchaseRequest) {
        Order order = new Order(
                purchaseRequest.getUserId()
        );

        Set<Integer> strBooksId = purchaseRequest.getBooks();
        Set<Book> books = new HashSet<>();

        strBooksId.forEach(bookTitle -> {
            Book book = bookRepository.findById(bookTitle).orElseThrow(() -> new RuntimeException("Error: Book is not found."));
            books.add(book);
        });

        order.setQuantity();
        order.setBooks(books);
        orderRepository.save(order);
        return ResponseEntity.ok(new MessageResponse("Purchase successfully"));
    }
}
