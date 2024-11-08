package store.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PromotionTest {

    @Test
    void 프로모션_적용이_가능한_날인지_확인() {
        // given
        LocalDate start = LocalDate.of(2024, 01, 01);
        LocalDate end = LocalDate.of(2024, 12, 31);
        Promotion promotion = new Promotion("탄산2+1", 2, 1, start, end);

        // when
        Boolean result = promotion.canBeApplied();

        //then
        assertThat(result).isTrue();
    }
}
