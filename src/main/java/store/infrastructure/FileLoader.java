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

    public List<Product> loadProducts() {
        String data = loadFile("src/main/resources/products.md");
        List<Product> products = new ArrayList<>();

        String[] lines = data.split("\n");

        for (int i = 1; i < lines.length; i++) {
            String[] fields = lines[i].split(",");

            String name = fields[0].trim();
            int price = Integer.parseInt(fields[1].trim());
            int quantity = Integer.parseInt(fields[2].trim());
            String promotion = fields[3].trim();

            Product product = new Product(name, price, quantity, promotion);
            products.add(product);
        }
        return products;
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
