package blackapple.webbook.models;

import java.util.*;

public class PurchaseRequest {

    private int userId;

    private Map<Integer,Integer> purchasedBooks;

    public int getUserId() {
        return userId;
    }

    public Map<Integer, Integer> getPurchasedBooks() {
        return purchasedBooks;
    }


    public PurchaseRequest(int userId, Map<Integer, Integer> purchasedBooks) {
        this.userId = userId;
        this.purchasedBooks = purchasedBooks;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "userId=" + userId +
                ", purchasedBooks=" + purchasedBooks +
                '}';
    }
}

