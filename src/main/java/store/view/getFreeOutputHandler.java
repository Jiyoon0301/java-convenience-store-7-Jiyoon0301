package store.view;

import camp.nextstep.edu.missionutils.Console;

public class getFreeOutputHandler {

    public Boolean promptGetFree(String name) {
        System.out.printf("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)", name);
        String answer = Console.readLine();
        if (answer == "Y") {
            return true;
        }
        return false;
    }
}
