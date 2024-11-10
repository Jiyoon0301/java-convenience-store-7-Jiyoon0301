package store.service;

import org.junit.jupiter.api.Test;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.PurchaseItem;
import store.domain.Receipt;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseServiceTest {

    private final PurchaseService purchaseService = new PurchaseService();

    @Test
    void 구매상품_객체_리스트를_올바르게_생성하는지_테스트() {
        // given
        Promotion promotion = null;
        Product product1 = new Product("콜라", 10, 10, 10, promotion);
        Product product2 = new Product("사이다", 10, 10, 10, promotion);
        PurchaseItem purchaseItem1 = new PurchaseItem(product1, 10);
        PurchaseItem purchaseItem2 = new PurchaseItem(product2, 10);
        List<PurchaseItem> expected = new ArrayList<>(List.of(purchaseItem1, purchaseItem2));

        Map<Product, Integer> productandQuantityPairs = new LinkedHashMap<>();
        productandQuantityPairs.put(product1, 10);
        productandQuantityPairs.put(product2, 10);

        // when
        List<PurchaseItem> result = purchaseService.createPurchaseItems(productandQuantityPairs);

        // then
        assertThat(result.get(0).getProduct().getName()).isEqualTo(product1.getName());
        assertThat(result.get(1).getProduct().getName()).isEqualTo(product2.getName());
        assertThat(result.size()).isEqualTo(expected.size());
    }

    @Test
    void 영수증_객체_생성이_올바른지_테스트() {
        // given
        Promotion promotion = null;
        Product product1 = new Product("콜라", 10, 10, 10, promotion);
        Product product2 = new Product("사이다", 10, 10, 10, promotion);
        PurchaseItem purchaseItem1 = new PurchaseItem(product1, 10);
        PurchaseItem purchaseItem2 = new PurchaseItem(product2, 10);
        List<PurchaseItem> purchaseItems = new ArrayList<>(List.of(purchaseItem1, purchaseItem2));
        String expected1 = "콜라";
        String expected2 = "사이다";

        // when
        Receipt receipt = purchaseService.createReceipt(purchaseItems);
        String result1 = receipt.getPurchaseItems().get(0).getProduct().getName();
        String result2 = receipt.getPurchaseItems().get(1).getProduct().getName();

        // then
        assertThat(result1).isEqualTo(expected1);
        assertThat(result2).isEqualTo(expected2);
    }

    @Test
    void 구매_수량만큼_재고_차감() {
        // given
        Promotion promotion = null;
        Product product1 = new Product("콜라", 10, -1, 10, promotion);
        Product product2 = new Product("사이다", 10, 10, -1, promotion);
        PurchaseItem purchaseItem1 = new PurchaseItem(product1, 10);
        PurchaseItem purchaseItem2 = new PurchaseItem(product2, 10);
        List<PurchaseItem> purchaseItems = new ArrayList<>(List.of(purchaseItem1, purchaseItem2));
        Receipt receipt = new Receipt(purchaseItems);
        int expected1 = 0;
        int expected2 = 0;

        // when
        purchaseService.removeStock(receipt);
        int result1 = receipt.getPurchaseItems().get(0).getProduct().getPromoStock();
        int result2 = receipt.getPurchaseItems().get(1).getProduct().getRegularStock();

        // then
        assertThat(result1).isEqualTo(expected1);
        assertThat(result2).isEqualTo(expected2);
    }
}
