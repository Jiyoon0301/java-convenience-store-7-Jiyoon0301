package store.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLoader {


    public String loadFile(String productsFilePath) {
        Path path = Paths.get(productsFilePath);

        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
