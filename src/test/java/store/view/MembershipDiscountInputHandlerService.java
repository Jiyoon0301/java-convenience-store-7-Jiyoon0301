package store.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.domain.ErrorMessage;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MembershipDiscountInputHandlerService {
    @ParameterizedTest
    @ValueSource(strings = {"a", "1", ","})
    void 입력이_올바르지_않을_경우_예외_발생(String input) {
        // when & then
        assertThatThrownBy(() -> MembershipDiscountInputHandler.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Y", "N"})
    void 입력이_올바르면_예외_없음(String input) {
        // when & then
        assertDoesNotThrow(() -> PayRegularPriceInputHandler.validateInput(input));
    }
}
