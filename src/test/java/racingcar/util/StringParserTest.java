package racingcar.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringParserTest {

    @Test
    @DisplayName("쉼표를 기준으로 이름을 정상 분리한다.")
    void splitCarNames_Success() {
        // given
        String input = "pobi,woni,jun";

        // when
        String[] names = StringParser.splitCarNames(input);

        // then
        assertThat(names).containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("마지막 쉼표가 있어도 빈 문자열을 포함하여 분리한다. (split limit -1)")
    void splitCarNames_WithTrailingDelimiter() {
        // given
        String input = "pobi,woni,"; // "pobi", "woni", ""

        // when
        String[] names = StringParser.splitCarNames(input);

        // then
        assertThat(names).containsExactly("pobi", "woni", "");
    }

    @Test
    @DisplayName("쉼표 사이 공백을 이름의 일부로 포함하여 분리한다.")
    void splitCarNames_WithPaddingSpaces() {
        // given
        String input = "pobi, woni"; // "pobi", " woni"

        // when
        String[] names = StringParser.splitCarNames(input);

        // then
        assertThat(names).containsExactly("pobi", " woni");
    }

    @Test
    @DisplayName("중간에 빈 문자열이 있어도 포함하여 분리한다.")
    void splitCarNames_WithEmptyStringInMiddle() {
        // given
        String input = "pobi,,jun"; // "pobi", "", "jun"

        // when
        String[] names = StringParser.splitCarNames(input);

        // then
        assertThat(names).containsExactly("pobi", "", "jun");
    }
}

