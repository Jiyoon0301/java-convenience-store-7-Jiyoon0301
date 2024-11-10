package store.infrastructure;

import camp.nextstep.edu.missionutils.DateTimes;
import org.junit.jupiter.api.Test;
import store.domain.Product;
import store.domain.Promotion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class FileLoaderTest {
    private FileLoader fileLoader = new FileLoader();

    @Test
    void 상품_파일로부터_객체_생성_테스트() throws IOException {
        // given
        String name = "콜라";
        int price = 1000;
        int regularStock = 10;
        int promoStock = 10;
        Promotion promotion = new Promotion("탄산2+1", 2, 1,
                LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 12, 31));
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(promotion);

        // when
        List<Product> products = fileLoader.loadProducts(promotions);
        Product result = products.get(0);

        // then
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getPrice()).isEqualTo(price);
        assertThat(result.getRegularStock()).isEqualTo(regularStock);
        assertThat(result.getPromoStock()).isEqualTo(promoStock);
        assertThat(result.getPromotion()).isEqualTo(promotion);
    }

    @Test
    void 프로모션_파일_불러오기() throws IOException {
        // given
        String name = "탄산2+1";
        int buy = 2;
        int get = 1;
        LocalDate startDate = LocalDate.of(2024,01,01);
        LocalDate endDate = LocalDate.of(2024,12,31);

        // when
        List<Promotion> promotions = fileLoader.loadPromotions();
        Promotion result = promotions.get(0);
        DateTimes.now();

        // then
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getBuy()).isEqualTo(buy);
        assertThat(result.getGet()).isEqualTo(get);
        assertThat(result.getStartDate()).isEqualTo(startDate);
        assertThat(result.getEndDate()).isEqualTo(endDate);
    }
}
