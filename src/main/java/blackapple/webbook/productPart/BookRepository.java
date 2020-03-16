package blackapple.webbook.productPart;

import blackapple.webbook.productPart.models.Book;
import blackapple.webbook.productPart.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
}
