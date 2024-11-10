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
}
