package store.infrastructure;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileLoaderTest {
    private FileLoader fileLoader = new FileLoader();

//    @BeforeEach
//    void setUp() {
//        fileLoader = new FileLoader();
//    }

    @Test
    void 상품_파일_불러오기() throws IOException {
        // given
        String productsFilePath = "src/main/resources/products.md";
        String expected = Files.readString(Paths.get(productsFilePath)).replaceAll("\\s+", " ").trim();

        // when
        String result = fileLoader.loadProducts();

        // then
        assertThat(result.replaceAll("\\s+", " ").trim()).isEqualTo(expected);
    }
}
