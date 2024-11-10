package store.domain;

import java.util.List;

public class Receipt {
    private List<PurchaseItem> purchaseItems;

    public Receipt(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }


    public int totalPurchaseAmount() {
        int sum = 0;
        for (PurchaseItem item : purchaseItems) {
            sum += item.getQuantity() * item.getProduct().getPrice();
        }
        return sum;
    }

    public int totalPromotionDiscountedAmount() {
        int sum = 0;
        for (PurchaseItem item : purchaseItems) {
            sum += item.discountedAmount();
        }
        return sum;
    }
}
