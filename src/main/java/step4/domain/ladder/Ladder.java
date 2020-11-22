package step4.domain.ladder;

import step4.domain.ladder.dto.LadderBuildDTO;
import step4.strategy.MakeLineStrategy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ladder {
    public static final String ERROR_INVALID_LADDER_HEIGHT = "사다리 높이는 0보다 커야 합니다.";

    private final LadderLine ladderLine;

    public Ladder(LadderLine ladderLine) {
        this.ladderLine = ladderLine;
    }

    public static Ladder of(List<Line> ladder) {
        return new Ladder(new LadderLine(ladder));
    }

    public static Ladder of(LadderBuildDTO buildDTO, MakeLineStrategy strategy) {
        isValidLineHeight(buildDTO.getLineHeight());

        return IntStream.range(0, buildDTO.getLineHeight())
                .mapToObj(index -> Line.of(buildDTO.getPlayers().count(), strategy))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Ladder::of));
    }

    public boolean isExistsPoint(Point point) {
        Line line = Optional.of(ladderLine.get(point.getY()))
                .orElseThrow(NoSuchElementException::new);
        return line.isExistsPoint(point);
    }

    public void forEach(Consumer<Line> function) {
        ladderLine.forEach(function);
    }

    public static void isValidLineHeight(int lineHeight) {
        if (lineHeight <= 0) {
            throw new IllegalArgumentException(ERROR_INVALID_LADDER_HEIGHT);
        }
    }

    public Point start(Point startPosition) {
        return ladderLine.getArrivalPoint(startPosition);
    }
}
