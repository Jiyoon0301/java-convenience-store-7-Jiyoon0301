package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.ErrorMessage;

public class AddFreeInputView {
    public static Boolean promptAskingAddFree(String productName) {
        while (true) {
            System.out.printf("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n", productName);
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
