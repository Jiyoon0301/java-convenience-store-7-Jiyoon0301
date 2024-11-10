package store.service;

import org.junit.jupiter.api.Test;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.PurchaseItem;

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

//    @Test
//    void 영수증_객체_생성이_올바른지_테스트() {
//        // given
//        Promotion promotion = null;
//        Product product1 = new Product("콜라", 10, 10, 10, promotion);
//        Product product2 = new Product("사이다", 10, 10, 10, promotion);
//        PurchaseItem purchaseItem1 = new PurchaseItem(product1, 10);
//        PurchaseItem purchaseItem2 = new PurchaseItem(product2, 10);
//        List<PurchaseItem> expected = new ArrayList<>(List.of(purchaseItem1, purchaseItem2));
//
//        Map<Product, Integer> productandQuantityPairs = new LinkedHashMap<>();
//        productandQuantityPairs.put(product1, 10);
//        productandQuantityPairs.put(product2, 10);
//
//        // when
//        List<PurchaseItem> result = purchaseService.createPurchaseItems(productandQuantityPairs);
//
//        // then
//        assertThat(result.get(0).getProduct().getName()).isEqualTo(product1.getName());
//        assertThat(result.get(1).getProduct().getName()).isEqualTo(product2.getName());
//        assertThat(result.size()).isEqualTo(expected.size());
//    }
}
