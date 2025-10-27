package racingcar.controller;

import racingcar.model.Cars;
import racingcar.util.StringParser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarGame {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingCarGame() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        Cars cars = createCarsFromInput();
        int roundCount = inputView.readRoundCount();

        runGameRounds(cars, roundCount);

        outputView.printFinalWinners(cars.getMaxNameList());
    }

    private Cars createCarsFromInput() {
        String[] carNames = StringParser.splitCarNames(inputView.readCarNames());
        return new Cars(carNames);
    }

    private void runGameRounds(Cars cars, int roundCount) {
        outputView.printRoundResultHeader();
        for (int i = 0; i < roundCount; i++) {
            cars.runRound();
            outputView.printRoundState(cars.getCarList());
        }
    }
}