package store.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductSelectionInputHandlerTest {
    @ParameterizedTest
    @ValueSource(strings = {"[콜라-10],[사이다-3", "콜라-10],[사이다-3]", "[콜라10,사이다3]"})
    void 입력_받은_개별_상품들은_대괄호로_묶여있지_않으면_예외_발생(String input) {
        assertThatThrownBy(() -> ProductSelectionInputHandler.validateProductSelection(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
    }

    @Test
    void 구매할_상품명과_수량이_하이픈으로_구별되어있지_않으면_예외_발생() {
        // given
        List<String> input = new ArrayList<>(List.of("콜라-10", "사이다3"));

        // when & then
        assertThatThrownBy(() -> ProductSelectionInputHandler.canSplitByHyphen(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
    }
}
