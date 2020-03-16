package blackapple.webbook.productPart;

import blackapple.webbook.productPart.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
