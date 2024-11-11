package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.ErrorMessage;

public class MembershipDiscountInputView {

    public static Boolean promptAskingMembershipDiscount() {
        while (true) {
            System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
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
