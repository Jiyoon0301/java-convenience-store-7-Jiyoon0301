package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductSelectionInputHandler {

    public static String promptProductSelection(List<Product> products) {

        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1]");

        String input = Console.readLine();
        validateProductSelection(input, products);
        return input;
    }

    public static Map<String, Integer> validateProductSelection(String input, List<Product> products) {
        List<String> noBrackets = validateEnclosedInBrackets(input);
        Map<String, String> noHyphen = canSplitByHyphen(noBrackets);
        validateProductExists(noHyphen, products);
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

    private static Map<String, String> canSplitByHyphen(List<String> input) {
        Map<String, String> noHyphen = new HashMap<>();
        for (String el : input) {
            if (! el.contains("-")) {
                throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
            }
            String[] pair = el.split("-");
            noHyphen.put(pair[0], pair[1]);
        }
        return noHyphen;
    }
    private static void validateProductExists(Map<String, String> noHyphen, List<Product> products) {
        for (String name : noHyphen.keySet()) {
            if (! doseExist(name, products)) {
                throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
            }
        }
    }
    private static Boolean doseExist(String name, List<Product> products) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}