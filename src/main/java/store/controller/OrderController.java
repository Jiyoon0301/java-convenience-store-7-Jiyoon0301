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
            Boolean again = process();
            if (!again) {
                break;
            }
        }
    }

    private Boolean process() {
        List<Product> products = createProduct();

        List<PurchaseItem> purchaseItems = selectProduct(products);

        adjustQuantity(purchaseItems);

        Receipt receipt = issueReceipt(purchaseItems);

        removeStock(receipt);

        return AdditionalPurchaseInputView.promptAdditionalPurchase();
    }

    private List<Product> createProduct() {
        List<Promotion> promotions = fileLoader.loadPromotions();
        return fileLoader.loadProducts(promotions);
    }

    private List<PurchaseItem> selectProduct(List<Product> products) {
        ProductOutputView.printProductList(products);
        Map<Product, Integer> productAndQuantityPairs = ProductSelectionInputView.promptProductSelection(products);
        return purchaseService.createPurchaseItems(productAndQuantityPairs);
    }

    private void adjustQuantity(List<PurchaseItem> purchaseItems) {
        for (PurchaseItem item : purchaseItems) {
            if (item.getProduct().getPromotion() != null && item.getProduct().getPromotion().canBeApplied()) {
                addQuantityIfPossible(item);
                decreaseQuantityIfCustomerWant(item);
            }
        }
    }

    private void addQuantityIfPossible(PurchaseItem item) {
        if (item.canBeAddFreeMore()) {
            Boolean answerAboutAddFree = AddFreeInputView.promptAskingAddFree(item.getProduct().getName());
            if (answerAboutAddFree) {
                item.addQuantity();
            }
        }
    }

    private void decreaseQuantityIfCustomerWant(PurchaseItem item) {
        if (item.getProduct().maxPromotionQuantity() < item.getQuantity()) {
            if (!PayRegularPriceInputView.promptAskingPayRegularPrice(item.getProduct().getName(), item.getQuantity() - item.getProduct().maxPromotionQuantity())) {
                item.decreaseQuantity(item.getQuantity() - item.getProduct().maxPromotionQuantity());
            }
        }
    }

    private Receipt issueReceipt(List<PurchaseItem> purchaseItems) {
        Receipt receipt = purchaseService.createReceipt(purchaseItems);

        Boolean membershipDiscountChoice = MembershipDiscountInputView.promptAskingMembershipDiscount();
        ReceiptOutputView.printReceipt(receipt, membershipDiscountChoice);

        return receipt;
    }

    private void removeStock(Receipt receipt) {
        purchaseService.removeStock(receipt);
    }
}

