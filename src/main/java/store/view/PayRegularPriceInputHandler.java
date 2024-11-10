package store.view;

import camp.nextstep.edu.missionutils.Console;

public class PayRegularPriceInputHandler {
    public static Boolean promptAskingPayRegularPrice(String productName, int productQuantity) {
        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n", productName, productQuantity);
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
