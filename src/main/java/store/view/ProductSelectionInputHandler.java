package store.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductSelectionInputHandler {

    public static String promptProductSelection() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1]");

        String input = Console.readLine();
        validateProductSelection(input);
        return input;
    }

    public static Map<String, Integer> validateProductSelection(String input) {
        List<String> withoutBrackets = validateEnclosedInBrackets(input);
        return null;
    }

    private static List<String> validateEnclosedInBrackets(String input) {
        String[] nameAndQuantityPair = input.split(",");
        List<String> result = new ArrayList<>();
        for (String nameAndQuantity : nameAndQuantityPair) {
            if (!nameAndQuantity.startsWith("[") || !nameAndQuantity.endsWith("]")) {
                throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
            }
            result.add(nameAndQuantity.substring(1, nameAndQuantity.length() - 1));
        }
        return result;
    }
}