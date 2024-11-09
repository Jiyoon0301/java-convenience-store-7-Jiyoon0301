package store.view;

import camp.nextstep.edu.missionutils.Console;

public class MembershipDiscountInputHandler {


    public static Boolean promptMembershipDiscount() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        String input = Console.readLine();
        return validateInput(input);
    }

    public static Boolean validateInput(String input) {
        if (!(input == "Y" || input == "N")) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
        }
        if (input == "Y") {
            return true;
        }
        return false;
    }
}
