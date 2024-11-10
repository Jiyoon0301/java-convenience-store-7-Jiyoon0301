package store.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReceiptTest {

    @Test
    void 총_구매액이_올바른지_테스트() {
        // given
        Promotion promotion = new Promotion("탄산2+1", 2, 1, null, null);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        List<PurchaseItem> purchaseItems = new ArrayList<>(List.of(new PurchaseItem(product, 5)));
        Receipt receipt = new Receipt(purchaseItems);
        int expected = product.getPrice() * 5;

        // when
        int result = receipt.totalPurchaseAmount();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
