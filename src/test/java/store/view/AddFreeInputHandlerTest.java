package store.view;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddFreeInputHandlerTest {
    @Test
    void 입력이_올바르지_않을_경우_예외_발생() {
        // given
        String input = "1";

        // when & then
        assertThatThrownBy(() -> AddFreeInputHandler.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
    }
}
