package blackapple.webbook.productPart.repository;

import blackapple.webbook.productPart.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
}
