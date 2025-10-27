package racingcar.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import racingcar.constant.ErrorMessage;

public class Cars {
    private final List<Car> carList;

    public Cars(String[] carNameList) {
        validateCarNameList(carNameList);
        this.carList = createCars(carNameList);
    }

    private void validateCarNameList(String[] carNameList) {
        if (carNameList == null || carNameList.length == 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CARS_BLANK_CAR_NAME.getMessage(carNameList));
        }

        Set<String> nameSet = new HashSet<>();
        for (String name : carNameList) {
            if (name.isBlank()) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_CARS_BLANK_CAR_NAME.getMessage(carNameList));
            }
            if (!nameSet.add(name)) {
                throw new IllegalArgumentException(
                        ErrorMessage.INVALID_CARS_DUPLICATE_CAR_NAME.getMessage(name));
            }
        }

    }

    private List<Car> createCars(String[] carNameList) {
        List<Car> newList = new ArrayList<>();
        for (String name : carNameList) {
            newList.add(new Car(name));
        }
        return newList;
    }

    public void runRound() {
        for (Car car : carList) {
            car.attemptMove();
        }
    }

    public List<String> getMaxNameList() {
        int max = -1;
        List<String> maxList = new ArrayList<>();
        for (Car car : carList) {
            if (car.getPosition() > max) {
                max = car.getPosition();
                maxList.clear();
                maxList.add(car.getName());
                continue;
            }
            if (car.getPosition() == max) {
                maxList.add(car.getName());
            }
        }
        return maxList;
    }

    public List<Car> getCarList() {
        return this.carList;
    }
}
