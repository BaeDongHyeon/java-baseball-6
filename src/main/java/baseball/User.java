package baseball;

import java.util.ArrayList;
import java.util.List;

public class User {

    public List<Integer> numbers = new ArrayList<>();

    public void setting(String numbersText) {
        valid(numbersText);

        clear();

        for (int i = 0; i < numbersText.length(); i++) {
            numbers.add(Integer.parseInt(numbersText.substring(i, i + 1)));
        }
    }

    private void clear() {
        numbers.clear();
    }

    private void valid(String numbersText) {
        if (numbersText.length() != 3) {
            throw new IllegalArgumentException("입력은 3자 미만이거나 초과할 수 없습니다.");
        }

        if (!numbersText.matches("[0-9]+")) {
            throw new IllegalArgumentException("입력은 숫자로만 할 수 있습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
