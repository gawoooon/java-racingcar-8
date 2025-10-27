package racingcar.util;

import java.util.Arrays;

public class StringParser {
    private static final String DELIMITER = ",";

    public static String[] splitCarNames(String input) {
        return Arrays.stream(input.split(DELIMITER, -1))
                .toArray(String[]::new);
    }
}
