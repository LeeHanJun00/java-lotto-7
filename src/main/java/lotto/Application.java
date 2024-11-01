package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        // 구입 금액 입력기능
        int inputMoney = Money.inputMoney();

        // 발행한 로또 수량을 출력
        int numberOfLotto = inputMoney / 1000;
        System.out.println("\n" + numberOfLotto + "개를 구매했습니다.");

        // 로또 발행 기능
        BuyLotto lottoList = new BuyLotto(numberOfLotto);
        lottoList.printLottoList();

        // 당첨번호 입력기능
        Lotto lotto = inputWinningNumbers();

    }

    private static Lotto inputWinningNumbers() {
        try {
            System.out.println("\n당첨 번호를 입력해 주세요.");
            String userInput = Console.readLine();
            List<Integer> userNumbers = parseIntegers(userInput);
            return new Lotto(userNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private static List<Integer> parseIntegers(final String userInput) {
        try {
            return Arrays.stream(userInput.split(","))
                    .map(number -> Integer.parseInt(number.trim()))
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효한 숫자를 입력하세요.");
        }
    }
}
