package racingcar.constant;

public enum ErrorMessage {
    INVALID_CAR_NAME_LENGTH("차의 이름은 공백 포함 5자 이하여야 합니다: "),
    INVALID_CAR_NAME_BLANK("차의 이름은 비울 수 없습니다: ");

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