package store.view;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductSelectionInputHandlerTest {
    @Test
    void 입력_받은_개별_상품들은_대괄호로_묶여있지_않으면_예외_발생() {
        String input = "[콜라-10],[사이다-3";

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThatThrownBy(() -> ProductSelectionInputHandler.promptProductSelection())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
    }
}
