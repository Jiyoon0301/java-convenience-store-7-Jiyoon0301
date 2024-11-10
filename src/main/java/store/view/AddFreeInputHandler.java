package store.view;

import camp.nextstep.edu.missionutils.Console;

public class AddFreeInputHandler {
    public static Boolean promptAskingAddFree(String productName) {
        System.out.printf("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n", productName);
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
