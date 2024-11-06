package store.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public String loadProducts() {
        return loadFile("src/main/resources/products.md");
    }

    public String loadPromotions() {
        return loadFile("src/main/resources/promotions.md");
    }
}
