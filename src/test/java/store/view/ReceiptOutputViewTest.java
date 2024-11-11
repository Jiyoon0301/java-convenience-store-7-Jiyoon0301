package store.view;

import org.junit.jupiter.api.Test;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.PurchaseItem;
import store.domain.Receipt;

import java.time.LocalDate;
import java.util.List;

public class ReceiptOutputViewTest {
    @Test
    void 영수증_출력_테스트() {
        LocalDate start = LocalDate.of(2024, 01, 01);
        LocalDate end = LocalDate.of(2024, 12, 31);
        Promotion promotion = new Promotion("탄산2+1", 2, 1, start, end);
        Product product1 = new Product("콜라", 1000, 10, 10, promotion);
        Product product2 = new Product("에너지바", 2000, 10, 10, null);
        PurchaseItem purchaseItem1 = new PurchaseItem(product1, 3);
        PurchaseItem purchaseItem2 = new PurchaseItem(product2, 5);
        Receipt receipt = new Receipt(List.of(purchaseItem1, purchaseItem2));
        ReceiptOutputView.printReceipt(receipt, true);
    }
}
