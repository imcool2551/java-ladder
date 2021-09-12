package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.stream.Stream;
import nextstep.ladder.strategy.RandomDrawLineStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LadderTest {

    @ParameterizedTest
    @MethodSource("provideGameSettings")
    @DisplayName("2 x 1 이상의 사다리를 생성할수있다.")
    void createLadder(LadderGameSettings settings) {
        assertThatCode(() -> Ladder.from(settings, RandomDrawLineStrategy.getInstance()))
            .doesNotThrowAnyException();
    }

    private static Stream<Arguments> provideGameSettings() {

        return Stream.of(
            Arguments.of(LadderGameSettings.of(
                Players.from(new String[]{"a", "b"}),
                LadderHeight.of(1),
                LadderGamePrizes.from(new String[]{"1", "2"}))),
            Arguments.of(LadderGameSettings.of(
                Players.from(new String[]{"a", "b"}),
                LadderHeight.of(2),
                LadderGamePrizes.from(new String[]{"1", "2"}))),
            Arguments.of(LadderGameSettings.of(
                Players.from(new String[]{"a", "b", "c"}),
                LadderHeight.of(10),
                LadderGamePrizes.from(new String[]{"1", "2", "3"})))
        );

    }

}