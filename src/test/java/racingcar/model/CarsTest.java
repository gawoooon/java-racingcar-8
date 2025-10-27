package racingcar.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import racingcar.constant.ErrorMessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @Test
    @DisplayName("유효한 자동차 이름들로 Cars 객체를 성공적으로 생성한다.")
    void createCars_Success() {
        // given
        String[] carNames = {"pobi", "woni", "jun"};

        // when
        Cars cars = new Cars(carNames);

        // then
        assertThat(cars).isNotNull();
        // 내부 carList의 사이즈를 확인하여 검증 (Reflection 사용)
        try {
            Field carListField = Cars.class.getDeclaredField("carList");
            carListField.setAccessible(true);
            List<Car> carList = (List<Car>) carListField.get(cars);
            assertThat(carList).hasSize(3);
            assertThat(carList.get(0).getName()).isEqualTo("pobi");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("자동차 이름 배열이 null일 경우 IllegalArgumentException을 던진다.")
    void createCars_WithNull_ShouldThrowException() {
        // given
        String[] carNames = null;

        // when & then
        assertThatThrownBy(() -> new Cars(carNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자동차 이름 배열이 비어있을 경우 IllegalArgumentException을 던진다.")
    void createCars_WithEmptyArray_ShouldThrowException() {
        // given
        String[] carNames = {};

        // when & then
        assertThatThrownBy(() -> new Cars(carNames))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("중복된 자동차 이름이 있을 경우 IllegalArgumentException을 던진다.")
    void createCars_WithDuplicateNames_ShouldThrowException() {
        // given
        String[] carNames = {"pobi", "woni", "pobi"};

        // when & then
        assertThatThrownBy(() -> new Cars(carNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_CARS_DUPLICATE_CAR_NAME.getMessage("pobi"));
    }

    @Test
    @DisplayName("이름이 공백인 자동차가 있을 경우 IllegalArgumentException을 던진다.")
    void createCars_WithBlankName_ShouldThrowException() {
        // given
        String[] carNames = {"pobi", " ", "jun"};

        // when & then
        assertThatThrownBy(() -> new Cars(carNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("가장 멀리 간 자동차가 한 대일 경우, 해당 자동차의 이름을 리스트로 반환한다.")
    void getMaxNameList_SingleWinner() throws Exception {
        // given
        String[] carNames = {"pobi", "woni", "jun"};
        Cars cars = new Cars(carNames);

        Field carListField = Cars.class.getDeclaredField("carList");
        carListField.setAccessible(true);
        List<Car> carList = (List<Car>) carListField.get(cars);

        // pobi: 3, woni: 5, jun: 2
        setPosition(carList.get(0), 3);
        setPosition(carList.get(1), 5);
        setPosition(carList.get(2), 2);

        // when
        List<String> winners = cars.getMaxNameList();

        // then
        assertThat(winners).containsExactly("woni");
    }

    @Test
    @DisplayName("가장 멀리 간 자동차가 여러 대일 경우, 모든 자동차의 이름을 리스트로 반환한다.")
    void getMaxNameList_MultipleWinners() throws Exception {
        // given
        String[] carNames = {"pobi", "woni", "jun"};
        Cars cars = new Cars(carNames);

        Field carListField = Cars.class.getDeclaredField("carList");
        carListField.setAccessible(true);
        List<Car> carList = (List<Car>) carListField.get(cars);

        // pobi: 5, woni: 3, jun: 5
        setPosition(carList.get(0), 5);
        setPosition(carList.get(1), 3);
        setPosition(carList.get(2), 5);

        // when
        List<String> winners = cars.getMaxNameList();

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "jun");
    }

    @Test
    @DisplayName("모든 자동차가 같은 위치에 있을 경우, 모든 자동차의 이름을 리스트로 반환한다.")
    void getMaxNameList_AllWinners() throws Exception {
        // given
        String[] carNames = {"pobi", "woni", "jun"};
        Cars cars = new Cars(carNames);

        Field carListField = Cars.class.getDeclaredField("carList");
        carListField.setAccessible(true);
        List<Car> carList = (List<Car>) carListField.get(cars);

        // pobi: 4, woni: 4, jun: 4
        setPosition(carList.get(0), 4);
        setPosition(carList.get(1), 4);
        setPosition(carList.get(2), 4);

        // when
        List<String> winners = cars.getMaxNameList();

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
    }


    private void setPosition(Car car, int position) throws Exception {
        Field positionField = Car.class.getDeclaredField("position");
        positionField.setAccessible(true);
        positionField.setInt(car, position);
    }
}