package blackapple.webbook.models;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderedBookId implements Serializable {

    private int orderId;
    private int productId;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedBookId that = (OrderedBookId) o;
        return orderId == that.orderId &&
                productId == that.productId;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 37 * result + productId;
        return result;
    }
}
