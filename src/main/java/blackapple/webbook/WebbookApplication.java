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

}
