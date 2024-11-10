package store.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.domain.ErrorMessage;
import store.domain.Product;
import store.domain.Promotion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductSelectionInputHandlerTest {
    @ParameterizedTest
    @ValueSource(strings = {"[콜라-10],[사이다-3", "콜라-10],[사이다-3]", "[콜라10,사이다3]"})
    void 입력_받은_개별_상품들은_대괄호로_묶여있지_않으면_예외_발생(String input) {
        // given
        List<Product> products = null;

        // when & then
        assertThatThrownBy(() -> ProductSelectionInputHandler.validateProductSelection(input, products))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_FORMAT.getMessage());
    }

    @Test
    void 구매할_상품명과_수량이_하이픈으로_구별되어있지_않으면_예외_발생() {
        // given
        String input = "[콜라-10],[사이다3]";
        List<Product> products = null;

        // when & then
        assertThatThrownBy(() -> ProductSelectionInputHandler.validateProductSelection(input, products))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_FORMAT.getMessage());
    }

    @Test
    void 존재하지_않는_상품이_포함되어_있으면_예외_발생() {
        // given
        String input = "[콜라-10],[포카칩-3]";
        Promotion promotion = new Promotion("탄산2+1", 2, 1, LocalDate.of(2024, 01, 01), LocalDate.of(2024, 12, 31));
        List<Product> products = new ArrayList<>(List.of(new Product("콜라", 1000, 10, 10, promotion)));

        // when & then
        assertThatThrownBy(() -> ProductSelectionInputHandler.validateProductSelection(input, products))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EXIST.getMessage());
    }

    @Test
    void 수량이_숫자만_있는_게_아니라면_예외_발생() {
        // given
        String input = "[콜라-a]";
        Promotion promotion = null;
        List<Product> products = new ArrayList<>(List.of(new Product("콜라", 1000, 10, 10, promotion)));

        // when & then
        assertThatThrownBy(() -> ProductSelectionInputHandler.validateProductSelection(input, products))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT.getMessage());
    }

    @Test
    void 수량이_재고를_초과하면_예외_발생() {
        // given
        String input = "[콜라-21]";
        Promotion promotion = null;
        List<Product> products = new ArrayList<>(List.of(new Product("콜라", 1000, 10, 10, promotion)));

        // when & then
        assertThatThrownBy(() -> ProductSelectionInputHandler.validateProductSelection(input, products))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OUT_OF_STOCK.getMessage());
    }
}
