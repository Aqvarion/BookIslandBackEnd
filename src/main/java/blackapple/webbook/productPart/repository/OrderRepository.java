package blackapple.webbook.productPart.repository;

import blackapple.webbook.productPart.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
