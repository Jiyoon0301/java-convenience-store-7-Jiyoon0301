package store.view;

import camp.nextstep.edu.missionutils.Console;

public class AdditionalPurchaseInputHandler {
    public static Boolean promptAdditionalPurchase() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String input = Console.readLine();
        return validateInput(input);
    }

    public static Boolean validateInput(String input) {
        if (!(input.equals("Y") || input.equals("N"))) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
        }
        if (input == "Y") {
            return true;
        }
        return false;
    }
}
