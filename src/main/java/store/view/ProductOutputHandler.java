package store.view;

import store.domain.Product;

import java.util.List;

public class ProductOutputHandler {

    public static void printProductList(List<Product> products) {
        promptProductList();

        for (Product product : products) {
            if (product.getPromoStock() != -1) {
                printDependingOnStock(product.getName(), product.getPrice(), product.getPromoStock(), product.getPromotion().getName());
            }
            printDependingOnStock(product.getName(), product.getPrice(), Math.max(0,product.getRegularStock()), "");
        }
        System.out.println();
    }

    private static void promptProductList() {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();
    }

    private static void printDependingOnStock(String name, int price, int stock, String promotionName) {
        if (stock == 0) {
            System.out.printf("- %s %,d원 재고 없음 %s\n", name, price, promotionName);
            return;
        }
        System.out.printf("- %s %,d원 %d개 %s\n", name, price, stock, promotionName);
    }
}
