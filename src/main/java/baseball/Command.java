package baseball;

import java.util.Arrays;

public enum Command {
    END("0"),
    RESTART("1");

    Command(String number) {
        this.number = number;
    }

    private final String number;

    public String getNumber() {
        return number;
    }

    public static Command findByNumber(String numberText) {
        valid(numberText);

        return Arrays.stream(Command.values())
                .filter(command -> numberText.equals(command.getNumber()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("옳지 않은 입력입니다."));
    }

    private static void valid(String numberText) {
        if (!numberText.matches("[0-9]+")) {
            throw new IllegalArgumentException("입력은 숫자로만 할 수 있습니다.");
        }
        if (!numberText.equals("1") && !numberText.equals("2")) {
            throw new IllegalArgumentException("시작 여부는 1 또는 2만 입력할 수 있습니다.");
        }
    }

}
