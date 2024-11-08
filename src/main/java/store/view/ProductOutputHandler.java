package store.view;

import store.domain.Product;

import java.util.List;

public class ProductOutputHandler {

    public static void printProductList(List<Product> products) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();

        for (Product product : products) {
            int stock = stockToPrint(product);
            String promotionName = promotionNameToPrint(product);
            System.out.printf(dependingOnStock(product.getName(), product.getPrice(), stock, promotionName));
        }
    }

    private static int stockToPrint(Product product) {
        if (product.getPromotion() == null){
            return product.getRegularQuantity();
        }
        return product.getPromoQuantity();
    }

    private static String dependingOnStock(String name, int price, int stock, String promotionName) {
        if (stock == 0) {
            return String.format("- %s %d원 재고 없음 %s\n", name, price, promotionName);
        }
        return String.format("- %s %d원 %d개 %s\n", name, price, stock, promotionName);
    }

    private static String promotionNameToPrint(Product product) {
        if (product.getPromotion() == null){
            return "";
        }
        return product.getPromotion().getName();
    }
}
