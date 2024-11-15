package store.service;

import store.domain.Product;
import store.domain.PurchaseItem;
import store.domain.Receipt;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PurchaseService {
    public List<PurchaseItem> createPurchaseItems(Map<Product, Integer> productandQuantityPairs) {
        List<PurchaseItem> purchaseItems = productandQuantityPairs.entrySet().stream()
                .map(entry -> new PurchaseItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return purchaseItems;
    }

    public Receipt createReceipt(List<PurchaseItem> purchaseItems) {
        return new Receipt(purchaseItems);
    }

    public void removeStock(Receipt receipt) {
        for (PurchaseItem item : receipt.getPurchaseItems()) {
            int numberToRemove = item.getQuantity();
            numberToRemove = removePromoStockIfMoreStockThanQuantity(item, numberToRemove);
            numberToRemove = removePromoStockIfMoreQuantityThanStock(item, numberToRemove);
            item.getProduct().removeRegularStock(numberToRemove);
        }
    }

    public int removePromoStockIfMoreStockThanQuantity(PurchaseItem item, int numberToRemove) {
        if (item.getProduct().getPromoStock() != -1 && item.getProduct().getPromoStock() >= numberToRemove) {
            item.getProduct().removePromoStock(numberToRemove);
            return 0;
        }
        return numberToRemove;
    }

    public int removePromoStockIfMoreQuantityThanStock(PurchaseItem item, int numberToRemove) {
        if (item.getProduct().getPromoStock() != -1 && item.getProduct().getPromoStock() < numberToRemove) {
            numberToRemove -= item.getProduct().getPromoStock();
            item.getProduct().removePromoStock(item.getProduct().getPromoStock());
        }
        return numberToRemove;
    }

}
