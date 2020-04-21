package blackapple.webbook.repositories;

import blackapple.webbook.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
