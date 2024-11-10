package store.domain;

import java.util.Objects;

public class Product {
    private final String name;
    private final int price;
    private Integer regularStock;
    private Integer promoStock;
    private Promotion promotion;

    public Product(String name, int price, int regularStock, int promoStock, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.regularStock = regularStock;
        this.promoStock = promoStock;
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

    public int getRegularStock() {
        return regularStock;
    }

    public int getPromoStock() {
        return promoStock;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void removeRegularStock(int num) {
        this.regularStock -= num;
    }

    public void removePromoStock(int num) {
        this.promoStock -= num;
    }

    public int maxPromotionQuantity() {
        return promoStock - promoStock % (promotion.getBuy() + 1);
    }
}
