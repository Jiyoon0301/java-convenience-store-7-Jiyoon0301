package store.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PayRegularPriceInputHandlerTest {
    @ParameterizedTest
    @ValueSource(strings = {"a", "1", ","})
    void 입력이_올바르지_않을_경우_예외_발생(String input) {
        // when & then
        assertThatThrownBy(() -> PayRegularPriceInputHandler.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Y", "N"})
    void 입력이_올바르면_예외_없음(String input) {
        // when & then
        assertThat(PayRegularPriceInputHandler.validateInput(input)).isTrue();
    }
}
