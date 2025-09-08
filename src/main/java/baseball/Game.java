package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Game {

    private Computer computer = new Computer();

    private User user = new User();

    private Map<String, Integer> gameResult = new HashMap<>();

    private Integer gameStatus = 1;

    public Game() {
        System.out.println("숫자 야구 게임을 시작합니다.");

        gradeReset();
    }

    public void play() {
        do {
            System.out.print("숫자를 입력해주세요 : ");

            user.setting(Console.readLine());

            check();

            result();

            gameEndCheck();
        } while (gameStatus == 1);
    }

    public void check() {
        List<Integer> computerNumbers = computer.getNumbers();

        for (int index = 0; index < 3; index++) {
            Integer userNumber = user.getNumbers().get(index);

            if (computerNumbers.contains(userNumber)) {
                if (Objects.equals(computerNumbers.get(index), userNumber)) {
                    gameResult.put("strike", gameResult.get("strike") + 1);
                } else {
                    gameResult.put("ball", gameResult.get("ball") + 1);
                }
            }
        }
    }

    public void result() {
        Integer strike = gameResult.get("strike");
        Integer ball = gameResult.get("ball");

        if (strike == 0 && ball == 0) {
            System.out.println("낫싱");
        } else {
            StringBuilder result = new StringBuilder();
            if (ball > 0) {
                result.append(ball).append("볼 ");
            }

            if (strike > 0) {
                result.append(strike).append("스트라이크");
            }
            System.out.println(result.toString().trim());
        }
    }

    public void reset() {
        computer.generate();

        gameResult.clear();
        gradeReset();
        gameStatus = 1;
    }

    private void gradeReset() {
        gameResult.put("ball", 0);
        gameResult.put("strike", 0);
    }

    private void gameEndCheck() {
        if (gameResult.get("strike") == 3) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String command = Console.readLine();
            if (!command.matches("[0-9]+")) {
                throw new IllegalArgumentException("입력은 숫자로만 할 수 있습니다.");
            }
            if (!command.equals("1") && !command.equals("2")) {
                throw new IllegalArgumentException("시작 여부는 1 또는 2만 입력할 수 있습니다.");
            }

            if (Integer.parseInt(command) == 1) {
                reset();
            } else {
                gameStatus = 0;
            }
        } else {
            gradeReset();
        }
    }
}
