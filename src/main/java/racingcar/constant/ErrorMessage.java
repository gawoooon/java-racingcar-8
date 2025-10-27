package racingcar.constant;

public enum ErrorMessage {
    INVALID_CAR_NAME_LENGTH("차의 이름은 공백 포함 5자 이하여야 합니다: "),
    INVALID_CAR_BLANK_NAME("차의 이름은 비울 수 없습니다: "),

    INVALID_CARS_BLANK_CAR_NAME("차 이름 리스트가 비었거나 null입니다: "),
    INVALID_CARS_DUPLICATE_CAR_NAME("차 이름 리스트에 중복되는 값이 존재합니다: "),

    INVALID_ROUND_COUNT_NUMBER("시도 횟수는 유효한 숫자여야 합니다: "),
    INVALID_ROUND_COUNT_POSITIVE("시도 횟수는 1 이상의 양수여야 합니다: ");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Object value) {
        return message + value;
    }
}