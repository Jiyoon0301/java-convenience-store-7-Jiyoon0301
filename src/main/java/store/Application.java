package store;

import store.controller.OrderController;
import store.infrastructure.FileLoader;
import store.service.PurchaseService;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        FileLoader fileLoader = new FileLoader();
        PurchaseService purchaseService = new PurchaseService();

        OrderController orderController = new OrderController(fileLoader, purchaseService);

        orderController.run();
    }
}
