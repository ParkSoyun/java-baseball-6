package baseball.controller;

import baseball.model.Number;
import baseball.model.Result;
import baseball.service.BaseballGameService;
import baseball.util.ConsoleIO;

public class BaseballGameController {
    private final BaseballGameService baseballGameService;

    public BaseballGameController() {
        baseballGameService = new BaseballGameService();
    }

    public void run() {
        ConsoleIO.printStartMessage();

        playRound();
    }

    private void playRound() {
        while (true) {
            Number computerNumber = baseballGameService.getComputerNumber();

            playSet(computerNumber);

            if (!baseballGameService.playNextRound(ConsoleIO.readEndOption())) {
                break;
            }
        }
    }

    private void playSet(Number computerNumber) {
        while (true) {
            Number playerNumber = baseballGameService.getPlayerNumber(ConsoleIO.readPlayerNumber());

            Result result = baseballGameService.umpire(computerNumber, playerNumber);
            int ball = result.getBall();
            int strike = result.getStrike();

            if (ball == 0 && strike == 3) {
                ConsoleIO.printResultMessage("3스트라이크");
                ConsoleIO.printEndMessage();

                break;
            }

            String resultMessage = "";

            if (ball == 0 && strike == 0) {
                resultMessage = "낫싱";
            } else if (ball == 0 && strike > 0) {
                resultMessage = strike + "스트라이크";
            } else if (ball > 0 && strike == 0) {
                resultMessage = ball + "볼";
            } else if (ball > 0 && strike > 0) {
                resultMessage = ball + "볼 " + strike + "스트라이크";
            }

            ConsoleIO.printResultMessage(resultMessage);
        }
    }
}