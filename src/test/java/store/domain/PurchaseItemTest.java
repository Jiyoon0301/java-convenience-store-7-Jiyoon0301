package store.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseItemTest {

    @Test
    void 추가_가능한_프로모션_수량을_반환한다() {
        // given
        LocalDate start = LocalDate.of(2024, 01, 01);
        LocalDate end = LocalDate.of(2024, 12, 31);
        Promotion promotion = new Promotion("탄산2+1", 2, 1, start, end);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        int quantity = 5;
        PurchaseItem purchaseItem = new PurchaseItem(product, quantity);

        // when
        Boolean result = purchaseItem.canBeAddFreeMore();

        // then
        assertThat(result).isTrue();
    }
}
