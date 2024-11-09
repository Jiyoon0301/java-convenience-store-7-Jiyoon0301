package store.service;

import org.junit.jupiter.api.Test;
import store.domain.Product;
import store.domain.PurchaseItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseServiceTest {

    private final PurchaseService purchaseService = new PurchaseService();
    @Test
    void 구매상품_객체_리스트를_올바르게_생성하는지_테스트() {
        // given
        Product product1 = null;
        Product product2 = null;
        PurchaseItem purchaseItem1 = new PurchaseItem(product1, 10);
        PurchaseItem purchaseItem2 = new PurchaseItem(product2, 10);
        List<PurchaseItem> expected = new ArrayList<>(List.of(purchaseItem1, purchaseItem2));

        Map<Product, Integer> productandQuantityPairs = new HashMap<>();
        productandQuantityPairs.put(product1, 10);
        productandQuantityPairs.put(product2, 10);

        // when
        List<PurchaseItem> result = purchaseService.createPurchaseItems(productandQuantityPairs);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
