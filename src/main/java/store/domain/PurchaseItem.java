package store.domain;

public class PurchaseItem {
    private final Product product;
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

    public Boolean canBeAddFreeMore() {
        int bundle = product.getPromotion().getBuy() + product.getPromotion().getGet();
        if (quantity < product.getPromoStock() && quantity % bundle == bundle - 1) {
            return true;
        }
        return false;
    }

    public int numberOfFree() {
        if (product.getPromotion() == null || !product.getPromotion().canBeApplied()) {
            return 0;
        }
        if (quantity <= product.getPromoStock()) {
            return quantity / (product.getPromotion().getBuy() + 1);
        }
        return product.getPromoStock() / (product.getPromotion().getBuy() + 1);
    }

    public int discountedAmount() {
        return numberOfFree() * product.getPrice();
    }

    public void addQuantity() {
        quantity++;
    }

    public void decreaseQuantity(int i) {
        quantity -= i;
    }
}
