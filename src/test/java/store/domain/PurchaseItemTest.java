package store.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseItemTest {

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

    @Test
    void 구매할_개별_상품의_프로모션_적용으로_할인된_금액() {
        // given
        Promotion promotion = new Promotion("탄산2+1", 2, 1, null, null);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        PurchaseItem purchaseItem = new PurchaseItem(product, 18);
        int expected = 3000;

        // when
        int result = purchaseItem.discountedAmount();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 구매할_상품의_수량_증가_테스트() {
        // given
        PurchaseItem purchaseItem = new PurchaseItem(null, 18);
        int expected = 19;

        // when
        purchaseItem.addQuantity();

        // given
        assertThat(purchaseItem.getQuantity()).isEqualTo(expected);
    }

    @Test
    void 구매할_상품의_수량_감소_테스트() {
        // given
        PurchaseItem purchaseItem = new PurchaseItem(null, 18);
        int expected = 16;

        // when
        purchaseItem.decreaseQuantity(2);

        // given
        assertThat(purchaseItem.getQuantity()).isEqualTo(expected);
    }
}
