package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.ErrorMessage;

public class AdditionalPurchaseInputView {
    public static Boolean promptAdditionalPurchase() {
        while (true) {
            System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
            String input = Console.readLine();
            try {
                return validateInput(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Boolean validateInput(String input) {
        if (!(input.equals("Y") || input.equals("N"))) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
        if (input == "Y") {
            return true;
        }
        return false;
    }
}
