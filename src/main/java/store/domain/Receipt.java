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

    public int totalQuantity() {
        int sum = 0;
        for (PurchaseItem item : purchaseItems) {
            sum += item.getQuantity();
        }
        return sum;
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

    private int totalPromotionAppliedAmount() {
        int sum = 0;
        for (PurchaseItem item : purchaseItems) {
            if (item.numberOfFree() != 0) {
                sum += item.numberOfFree() * (item.getProduct().getPromotion().getBuy() + 1) * item.getProduct().getPrice();
            }
        }
        return sum;
    }

    public int membershipDiscountedAmount() {
        int amount = totalPurchaseAmount() - totalPromotionAppliedAmount();
        return Math.min(amount / 10 * 3, 8000);
    }

    public int finalPaymentAmount(Boolean membershipDiscountChoice) {
        if (membershipDiscountChoice) {
            return totalPurchaseAmount() - totalPromotionDiscountedAmount() - membershipDiscountedAmount();
        }
        return totalPurchaseAmount() - totalPromotionDiscountedAmount();
    }
}
