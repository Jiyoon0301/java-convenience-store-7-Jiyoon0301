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

    @Test
    void 총_할인_금액이_올바른지_테스트() {
        // given
        Promotion promotion = new Promotion("탄산2+1", 2, 1, null, null);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        List<PurchaseItem> purchaseItems = new ArrayList<>(List.of(new PurchaseItem(product, 7)));
        Receipt receipt = new Receipt(purchaseItems);
        int expected = 2000;

        // when
        int result = receipt.totalPromotionDiscountedAmount();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 멤버십_할인_금액이_올바른지_테스트() {
        // given
        Promotion promotion = new Promotion("탄산2+1", 2, 1, null, null);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        List<PurchaseItem> purchaseItems = new ArrayList<>(List.of(new PurchaseItem(product, 7)));
        Receipt receipt = new Receipt(purchaseItems);
        int expected = 1500;

        // when
        int result = receipt.membershipDiscountedAmount();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 최종_결제할_금액이_올바른지_테스트() {
        // given
        Promotion promotion = new Promotion("탄산2+1", 2, 1, null, null);
        Product product = new Product("콜라", 1000, 10, 10, promotion);
        List<PurchaseItem> purchaseItems = new ArrayList<>(List.of(new PurchaseItem(product, 7)));
        Receipt receipt = new Receipt(purchaseItems);
        Boolean membershipDiscountChoice = true;
        int expected = 3500;

        // when
        int result = receipt.finalPaymentAmount(membershipDiscountChoice);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
