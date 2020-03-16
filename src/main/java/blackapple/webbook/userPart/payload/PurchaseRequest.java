package blackapple.webbook.userPart.payload;

import blackapple.webbook.productPart.models.Book;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PurchaseRequest {

    private int userId;

    private Set<Integer> books = new HashSet<>();

    private int quantity;

    // id books with his quantity
    // private Map<Book,Integer> bookss = new HashMap<>();

    public int getUserId() {
        return userId;
    }

    public Set<Integer> getBooks() {
        return books;
    }

    public int getQuantity() {
        return quantity;
    }
}

