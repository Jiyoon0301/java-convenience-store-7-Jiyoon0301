package store.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {
    @Test
    void 구매할_상품의_프로모션_적용_가능한_최대_수량_반환() {
        // given
        Promotion promotion = new Promotion("탄산2+1", 2, 1, null, null);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        int expected = 9;

        // when
        int result = product.maxPromotionQuantity();

        // given
        assertThat(result).isEqualTo(expected);
    }
}
