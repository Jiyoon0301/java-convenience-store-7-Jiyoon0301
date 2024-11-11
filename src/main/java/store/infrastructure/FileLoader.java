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

        String[] lines = data.split("\n");

        List<Product> products = processProductLine(promotions, new ArrayList<>(), lines);

        return products;
    }

    private List<Product> processProductLine(List<Promotion> promotions, List<Product> products, String[] lines) {
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

            products.add(createNewProduct(promotions, promotionName, name, price, stock));
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

    private Product createNewProduct(List<Promotion> promotions, String promotionName, String productName, int price, int stock) {
        Promotion promotion = findPromotionByName(promotions, promotionName);

        if (promotionName.equals("null")) {
            Product product = new Product(productName, price, stock, -1, promotion);
            return product;
        }
        Product product = new Product(productName, price, -1, stock, promotion);
        return product;
    }

    public List<Promotion> loadPromotions() {
        String data = loadFile("src/main/resources/promotions.md");
        List<Promotion> promotions = new ArrayList<>();

        String[] lines = data.split("\n");

        for (int i = 1; i < lines.length; i++) {
            Promotion promotion = splitPromotionData(lines[i]);
            promotions.add(promotion);
        }
        return promotions;
    }

    private Promotion splitPromotionData(String line) {
        String[] fields = line.split(",");

        String name = fields[0].trim();
        int buy = Integer.parseInt(fields[1].trim());
        int get = Integer.parseInt(fields[2].trim());
        LocalDate startDate = LocalDate.parse(fields[3].trim());
        LocalDate endDate = LocalDate.parse(fields[4].trim());

        return new Promotion(name, buy, get, startDate, endDate);
    }
}
