package store.domain;

public class PurchaseItem {
    private Product product;
    private int quantity;

    public PurchaseItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int promotionApplicableQuantity() {
        return product.getPromoQuantity() / (product.getPromotion().getBuy() + product.getPromotion().getGet());
    }

    public Boolean canBeAddFreeMore() {
        int bundle = product.getPromotion().getBuy() + product.getPromotion().getGet();
        if (quantity % bundle == bundle - 1) {
            return true;
        }
        return false;
    }

    public int numberOfFree() {
        if (product.getPromotion() == null || !product.getPromotion().canBeApplied()) {
            return 0;
        }
        if (quantity <= product.getPromoQuantity()) {
            return quantity / (product.getPromotion().getBuy() + 1);
        }
        return product.getPromoQuantity() / (product.getPromotion().getBuy() + 1);
    }

    public int discountedAmount() {
        return numberOfFree() * product.getPrice();
    }
}
