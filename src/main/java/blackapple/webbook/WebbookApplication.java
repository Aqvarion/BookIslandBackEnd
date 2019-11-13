package blackapple.webbook;

import blackapple.webbook.entities.Book;
import blackapple.webbook.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class WebbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebbookApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BookRepository bookRepository) {
        return args -> {
//            Stream.of("Book1", "Book2").forEach(title -> Stream.of("Genre1","Genre2").forEach(genre -> {
//                Book book = new Book(title, genre);
//                bookRepository.save(book);
//            }));
            Book book = new Book("Book1","Genre1");
            bookRepository.save(book);
            book = new Book("Book2","Genre2");
            bookRepository.save(book);
            bookRepository.findAll().forEach(System.out::println);
        };
    }

}
