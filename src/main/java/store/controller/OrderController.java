package store.controller;

import store.domain.Product;
import store.domain.Promotion;
import store.domain.PurchaseItem;
import store.infrastructure.FileLoader;
import store.service.PurchaseService;
import store.view.AddFreeInputHandler;
import store.view.ProductOutputHandler;
import store.view.ProductSelectionInputHandler;

import java.util.List;
import java.util.Map;

public class OrderController {
    private final FileLoader fileLoader;
    private final PurchaseService purchaseService;

    public OrderController(FileLoader fileLoader, PurchaseService purchaseService) {
        this.fileLoader = fileLoader;
        this.purchaseService = purchaseService;
    }
    public void run() {
        List<Promotion> promotions = fileLoader.loadPromotions();
        List<Product> products = fileLoader.loadProducts(promotions);

        ProductOutputHandler.printProductList(products);
        Map<Product, Integer> productAndQuantityPairs = ProductSelectionInputHandler.promptProductSelection(products);
        List<PurchaseItem> purchaseItems = purchaseService.createPurchaseItems(productAndQuantityPairs);

        for (PurchaseItem item : purchaseItems) {
            if (item.getProduct().getPromotion() != null && item.getProduct().getPromotion().canBeApplied()) {
                if (item.canBeAddFreeMore()) {
                    Boolean answerAboutAddFree = AddFreeInputHandler.promptAskingAddFree(item.getProduct().getName());
                    if (answerAboutAddFree) {
//                        item.addQuantity();
                    }
                }
            }
        }

    }
}
