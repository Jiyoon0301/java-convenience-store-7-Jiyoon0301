package store.view;

import store.domain.PurchaseItem;
import store.domain.Receipt;

public class ReceiptOutputView {
    public static void printReceipt(Receipt receipt, Boolean membership) {
        System.out.println("==============W 편의점================");
        printNameQuantityAmount(receipt);
        System.out.println("=============증     정===============");
        printFree(receipt);
        System.out.println("====================================");
        printFinalResult(receipt, membership);
    }

    private static void printNameQuantityAmount(Receipt receipt) {
        System.out.printf("%-18s%-8s%-4s\n", "상품명", "수량", "금액");
        for (PurchaseItem item : receipt.getPurchaseItems()) {
            System.out.printf("%-18s%-8d%,-4d\n", item.getProduct().getName(), item.getQuantity(), item.getQuantity() * item.getProduct().getPrice());
        }
    }

    private static void printFree(Receipt receipt) {
        for (PurchaseItem item : receipt.getPurchaseItems()) {
            if (item.numberOfFree() != 0 && item.getProduct().getPromotion().canBeApplied()) {
                System.out.printf("%-18s%-8d\n", item.getProduct().getName(), item.numberOfFree());
            }
        }
    }

    private static void printFinalResult(Receipt receipt, Boolean membership) {
        int totalAmount = receipt.totalPurchaseAmount();
        System.out.printf("%-18s%-8d%,4d\n", "총구매액", receipt.totalQuantity(), receipt.totalPurchaseAmount());
        System.out.printf("%s%,30d\n", "행사할인", -receipt.totalPromotionDiscountedAmount());
        System.out.printf("%s%,30d\n", "멤버십할인", -membershipDiscountedAmount(receipt, membership));
        System.out.printf("%s%,30d\n", "내실돈", totalAmount - receipt.totalPromotionDiscountedAmount() - membershipDiscountedAmount(receipt, membership));
    }

    private static int membershipDiscountedAmount(Receipt receipt, Boolean membership) {
        if (membership) {
            return receipt.membershipDiscountedAmount();
        }
        return 0;
    }
}
