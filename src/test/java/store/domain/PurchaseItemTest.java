package store.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseItemTest {

    @Test
    void 프로모션_적용_가능한_수량을_반환() {
        // given
        Promotion promotion = new Promotion("탄산2+1", 2, 1, null, null);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        PurchaseItem purchaseItem = new PurchaseItem(product, 5);
        int expected = product.getPromoQuantity() / (promotion.getBuy() + promotion.getGet());

        // when
        int result = purchaseItem.promotionApplicableQuantity();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 추가_가능한_프로모션_수량이_있는지_반환한다() {
        // given
        Promotion promotion = new Promotion("탄산2+1", 2, 1, null, null);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        int quantity = 5;
        PurchaseItem purchaseItem = new PurchaseItem(product, quantity);

        // when
        Boolean result = purchaseItem.canBeAddFreeMore();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 증정_수량_반환_프로모션_재고가_충분한_경우() {
        // given
        Promotion promotion = new Promotion("탄산2+1", 2, 1, null, null);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        PurchaseItem purchaseItem = new PurchaseItem(product, 10);
        int expected = 3;

        // when
        int result = purchaseItem.numberOfFree();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 증정_수량_반환_프로모션_재고가_부족한_경우() {
        // given
        Promotion promotion = new Promotion("탄산2+1", 2, 1, null, null);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        PurchaseItem purchaseItem = new PurchaseItem(product, 13);
        int expected = 3;

        // when
        int result = purchaseItem.numberOfFree();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
