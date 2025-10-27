package racingcar.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    @Test
    @DisplayName("유효한 양의 정수 문자열을 숫자로 변환하여 반환한다.")
    void validateRoundCount_Success() {
        // given
        String input = "5";

        // when
        int roundCount = InputValidator.validateRoundCount(input);

        // then
        assertThat(roundCount).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1.5", " 5"})
    @DisplayName("정수로 변환할 수 없는 문자열이 입력되면 예외를 발생시킨다.")
    void validateRoundCount_NotANumber_ShouldThrowException(String invalidInput) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateRoundCount(invalidInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-100"})
    @DisplayName("0 또는 음의 정수가 입력되면 예외를 발생시킨다.")
    void validateRoundCount_ZeroOrNegative_ShouldThrowException(String nonPositiveInput) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateRoundCount(nonPositiveInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}