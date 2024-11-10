package store.infrastructure;

import store.domain.Product;
import store.domain.Promotion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    private String loadFile(String productsFilePath) {
        Path path = Paths.get(productsFilePath);

        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public List<Product> loadProducts(List<Promotion> promotions) {
        String data = loadFile("src/main/resources/products.md");
        List<Product> products = new ArrayList<>();

        String[] lines = data.split("\n");

        for (int i = 1; i < lines.length; i++) {
            String[] fields = lines[i].split(",");

            String name = fields[0].trim();
            int price = Integer.parseInt(fields[1].trim());
            int stock = Integer.parseInt(fields[2].trim());
            String promotionName = fields[3].trim();
            Product existingProduct = findProductByName(products, name);
            if (existingProduct != null && promotionName.equals("null")) { // 프로모션 없는 기존 제품
                existingProduct.addRegularStock(stock + 1);
                continue;
            }
            if (existingProduct != null && !promotionName.equals("null")) { // 프로모션 있는 기존 제품
                existingProduct.addPromoStock(stock + 1);
                continue;
            }

            Promotion promotion = findPromotionByName(promotions, promotionName);

            if (promotionName.equals("null")) {
                Product product = new Product(name, price, stock, -1, promotion);
                products.add(product);
            }
            if (!promotionName.equals("null")) {
                Product product = new Product(name, price, -1, stock, promotion);
                products.add(product);
            }
        }
        return products;
    }

    private Product findProductByName(List<Product> products, String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    private Promotion findPromotionByName(List<Promotion> promotions, String name) {
        for (Promotion promotion : promotions) {
            if (promotion.getName().equals(name)) {
                return promotion;
            }
        }
        return null;
    }

    public List<Promotion> loadPromotions() {
        String data = loadFile("src/main/resources/promotions.md");
        List<Promotion> promotions = new ArrayList<>();

        String[] lines = data.split("\n");

        for (int i = 1; i < lines.length; i++) {
            String[] fields = lines[i].split(",");

            String name = fields[0].trim();
            int buy = Integer.parseInt(fields[1].trim());
            int get = Integer.parseInt(fields[2].trim());
            LocalDate startDate = LocalDate.parse(fields[3].trim());
            LocalDate endDate = LocalDate.parse(fields[4].trim());

            Promotion promotion = new Promotion(name, buy, get, startDate, endDate);
            promotions.add(promotion);
        }
        return promotions;
    }
}
