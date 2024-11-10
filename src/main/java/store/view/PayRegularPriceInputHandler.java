package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.ErrorMessage;

public class PayRegularPriceInputHandler {
    public static Boolean promptAskingPayRegularPrice(String productName, int productQuantity) {
        while (true) {
            System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n", productName, productQuantity);
            String input = Console.readLine();
            try {
                return validateInput(input);
            }catch (IllegalArgumentException e) {
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
