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

    public Game() {
        System.out.println("숫자 야구 게임을 시작합니다.");

        gradeReset();
    }

    public void play() {
        System.out.print("숫자를 입력해주세요 : ");

        user.setting(Console.readLine());
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
    }

    private void gradeReset() {
        gameResult.put("ball", 0);
        gameResult.put("strike", 0);
    }
}
