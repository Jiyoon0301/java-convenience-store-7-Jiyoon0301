package store.infrastructure;

import org.junit.jupiter.api.Test;
import store.domain.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class FileLoaderTest {
    private FileLoader fileLoader = new FileLoader();

    @Test
    void 상품_파일로부터_객체_생성_테스트() throws IOException {
        // given
        String name = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = "탄산2+1";

        // when
        List<Product> products = fileLoader.loadProducts();
        Product result = products.get(0);

        // then
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getPrice()).isEqualTo(price);
        assertThat(result.getQuantity()).isEqualTo(quantity);
        assertThat(result.getPromotion()).isEqualTo(promotion);
    }

//    @Test
//    void 프로모션_파일_불러오기() throws IOException {
//        // given
//        String productsFilePath = "src/main/resources/promotions.md";
//        String expected = Files.readString(Paths.get(productsFilePath)).replaceAll("\\s+", " ").trim();
//
//        // when
//        String result = fileLoader.loadPromotions();
//
//        // then
//        assertThat(result.replaceAll("\\s+", " ").trim()).isEqualTo(expected);
//    }
}
