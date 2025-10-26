package racingcar.model;

import racingcar.constant.ErrorMessage;
import camp.nextstep.edu.missionutils.Randoms;

public class Car {

    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_RANDOM_NUMBER = 0;
    private static final int MAX_RANDOM_NUMBER = 9;
    private static final int MOVE_START = 4;

    private final String name;
    private int position;

    public Car(String name) {
        validateName(name);
        this.name = name;
        position = 0;
    }

    private void validateName(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CAR_NAME_LENGTH.getMessage(name));
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CAR_NAME_BLANK.getMessage(name));
        }
    }

    public void attemptMove() {
        int num = Randoms.pickNumberInRange(MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER);
        if (num >= MOVE_START) {
            position++;
        }
    }

    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return this.position;
    }
}
