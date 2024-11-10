package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.Product;

import java.util.*;

public class ProductSelectionInputHandler {

    public static Map<Product, Integer> promptProductSelection(List<Product> products) {

        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1]");

        String input = Console.readLine();
        Map<Product, Integer> productAndQuantityParis = validateProductSelection(input, products);
        return productAndQuantityParis;
    }

    public static Map<Product, Integer> validateProductSelection(String input, List<Product> products) {
        List<String> noBrackets = validateEnclosedInBrackets(input);
        Map<String, String> noHyphen = canSplitByHyphen(noBrackets);
        Map<Product, String> productAndQuantityPaires = validateProductExists(noHyphen, products);
        Map<Product, Integer> productAndQuantityParis = validateQuantity(productAndQuantityPaires);
        checkStock(productAndQuantityParis);
        return productAndQuantityParis;
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
        Map<String, String> noHyphen = new LinkedHashMap<>();
        for (String el : input) {
            if (!el.contains("-")) {
                throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
            }
            String[] pair = el.split("-");
            noHyphen.put(pair[0], pair[1]);
        }
        return noHyphen;
    }

    private static Map<Product, String> validateProductExists(Map<String, String> noHyphen, List<Product> products) {
        Map<Product, String> productAndQuantity = new LinkedHashMap<>();
        for (String name : noHyphen.keySet()) {
            productAndQuantity.put(doseExist(name, products), noHyphen.get(name));
        }
        return productAndQuantity;
    }

    private static Product doseExist(String name, List<Product> products) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
    }

    private static Map<Product, Integer> validateQuantity(Map<Product, String> noHyphen) {
        Map<Product, Integer> nameAndQuantityPairs = new LinkedHashMap<>();
        for (Map.Entry<Product, String> entry : noHyphen.entrySet()) {
            try {
                Integer.parseInt(entry.getValue());
            } catch (Exception e) {
                throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
            }
            nameAndQuantityPairs.put(entry.getKey(), Integer.parseInt(entry.getValue()));
        }
        return nameAndQuantityPairs;
    }

    private static void checkStock(Map<Product, Integer> input) {
        for (Map.Entry<Product, Integer> entry : input.entrySet()) {
            int promoStock = Math.max(entry.getKey().getPromoStock(), 0);
            int regularStock = Math.max(entry.getKey().getRegularStock(), 0);
            if (promoStock + regularStock < entry.getValue()) {
                throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }
        }
    }
}