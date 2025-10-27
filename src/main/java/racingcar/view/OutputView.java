package racingcar.view;

import java.util.List;
import racingcar.model.Car;

public class OutputView {

    private static final String ROUND_RESULT_HEADER = "\n실행 결과";
    private static final String POSITION_BAR = "-";
    private static final String WINNER_HEADER = "최종 우승자 : ";
    private static final String WINNER_DELIMITER = ", ";

    public void printRoundResultHeader() {
        System.out.println(ROUND_RESULT_HEADER);
    }

    public void printRoundState(List<Car> cars) {
        for (Car car : cars) {
            String positionDisplay = POSITION_BAR.repeat(car.getPosition());
            System.out.printf("%s : %s\n", car.getName(), positionDisplay);
        }
        System.out.println();
    }

    public void printFinalWinners(List<String> winnerNames) {
        String winners = String.join(WINNER_DELIMITER, winnerNames);
        System.out.println(WINNER_HEADER + winners);
    }
}