package racingcar.validation;

import racingcar.constant.ErrorMessage;

public class InputValidator {

    public static int validateRoundCount(String input) {
        int roundCount;
        try {
            roundCount = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ROUND_COUNT_NUMBER.getMessage(input));
        }

        if (roundCount <= 0) {
            throw new IllegalArgumentException(
                    ErrorMessage.INVALID_ROUND_COUNT_POSITIVE.getMessage(String.valueOf(roundCount)));
        }
        return roundCount;
    }
}