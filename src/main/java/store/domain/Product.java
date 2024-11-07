package store.domain;

import java.util.Objects;

public class Product {
    private final String name;
    private final int price;
    private int regularStock;
    private int promoStock;
    private Promotion promotion;

    public Product(String name, int price, int regularQuantity, int promoQuantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.regularStock = regularQuantity;
        this.promoStock = promoQuantity;
        this.promotion = promotion;
    }

    public void addRegularStock(int num) {
        this.regularStock += num;
    }

    public void addPromoStock(int num) {
        this.promoStock += num;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRegularQuantity() {
        return regularStock;
    }

    public int getPromoQuantity() {
        return promoStock;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
