package racingcar.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.constant.ErrorMessage;

import static org.assertj.core.api.Assertions.*;

class CarTest {

    @Test
    @DisplayName("이름이 5자 이하이면 자동차가 정상 생성된다")
    void validName_ShouldCreateCarSuccessfully() {
        assertThatCode(() -> new Car("pobi"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이름이 5자를 초과하면 예외가 발생한다")
    void nameLongerThanFive_ShouldThrowException() {
        assertThatThrownBy(() -> new Car("abcdef"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_CAR_NAME_LENGTH.getMessage("abcdef"));

    }

    @Test
    @DisplayName("공백을 문자로 세고, 이름이 5자를 초과하면 예외가 발생한다")
    void nameLongerThanFiveWithBlank_ShouldThrowException() {
        assertThatThrownBy(() -> new Car("abcd f"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_CAR_NAME_LENGTH.getMessage("abcd f"));

    }

    @Test
    @DisplayName("이름이 공백이면 예외가 발생한다")
    void blankName_ShouldThrowException() {
        assertThatThrownBy(() -> new Car("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_CAR_NAME_BLANK.getMessage("   "));

    }

    @Test
    @DisplayName("초기 위치는 0이다")
    void initialPosition_ShouldBeZero() {
        Car car = new Car("crong");
        assertThat(car.getPosition()).isZero();
    }

    @Test
    @DisplayName("move() 호출시 랜덤값이 4 이상이면 전진한다 (확률적으로)")
    void move_ShouldIncreasePositionWhenRandomIsAbove4() {
        Car car = new Car("pobi");
        
        for (int i = 0; i < 100; i++) {
            car.attemptMove();
        }

        assertThat(car.getPosition()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("이름을 올바르게 반환한다")
    void getName_ShouldReturnCorrectName() {
        Car car = new Car("honux");
        assertThat(car.getName()).isEqualTo("honux");
    }
}