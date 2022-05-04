package nextstep.ladder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LadderTest {

    @DisplayName("createLadder는 width 폭, height 높이의 사다리를 만든다.")
    @Test
    void createLadder() {
        Ladder ladder = Ladder.createRandomLadder(4, 5);

        assertThat(ladder.getWidth()).isEqualTo(4);
        assertThat(ladder.getLines()).hasSize(5);
    }

    @ParameterizedTest(name = "사다리는 0보다 큰 width, height 를 가진다.")
    @CsvSource(value = {"0,0", "0,2", "2,0"})
    void createLadder_Exception(int width, int height) {
        assertThatThrownBy(() -> Ladder.createRandomLadder(width, height))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
