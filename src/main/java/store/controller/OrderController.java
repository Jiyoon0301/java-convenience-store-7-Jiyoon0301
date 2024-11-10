package store.controller;

import store.domain.Product;
import store.domain.Promotion;
import store.domain.PurchaseItem;
import store.domain.Receipt;
import store.infrastructure.FileLoader;
import store.service.PurchaseService;
import store.view.*;

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
        while (true) {
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
                            item.addQuantity();
                        }

                        if (item.getProduct().maxPromotionQuantity() < item.getQuantity()) {
                            if (!PayRegularPriceInputHandler.promptAskingPayRegularPrice(item.getProduct().getName(), item.getQuantity() - item.getProduct().maxPromotionQuantity())) {
                                item.decreaseQuantity(item.getQuantity() - item.getProduct().maxPromotionQuantity());
                            }
                        }
                    }
                }
            }

            Boolean membershipDiscountChoice = MembershipDiscountInputHandler.promptAskingMembershipDiscount();

            Receipt receipt = purchaseService.createReceipt(purchaseItems);
            ReceiptOutputHandler.printReceipt(receipt, membershipDiscountChoice);

            Boolean answerAbountAdditionalPurchase = AdditionalPurchaseInputHandler.promptAdditionalPurchase();
            if (!answerAbountAdditionalPurchase) {
                break;
            }
        }
    }
}
