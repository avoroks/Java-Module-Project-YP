import static java.util.regex.Pattern.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private static final String START_TEXT = "Для добавления нового товара введите его название и стоимость в формате: <НАЗВАНИЕ РУБЛИ.КОПЕЙКИ> и нажмите <Enter>. " +
            "Пример: <кофе 150.05>. Если вы закончили добавлять товары, то введите: <завершить>.";
    private static final String PATTERN = "(.+(\\s+.+)*)*\\s+([0-9]+\\.[0-9]{2})$";

    public List<Product> products = new ArrayList<>();

    public void parseNewProducts() {
        System.out.println(START_TEXT);
        String line;

        while (true) {
            try {
                line = Main.scanner.nextLine();
                if (line.equalsIgnoreCase("завершить")) break;

                Pattern pattern = compile(PATTERN);
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    var productNameWithoutSpaces = matcher.group(1).trim();
                    if (productNameWithoutSpaces.length() == 0) throw new IllegalArgumentException("Недопустимый продукт");
                    products.add(new Product(productNameWithoutSpaces, Double.valueOf(matcher.group(3))));
                    System.out.println("Отлично. Добавили продукт с именем = '" + productNameWithoutSpaces + "' и стоимостью = " + matcher.group(3) + ". Добавляйте продукты еще или напишите <завершить>.");
                } else System.out.println("Что-то не так. " + START_TEXT);
            } catch (Exception __) {
                System.out.println("Что-то не так. " + START_TEXT);
            }
        }
    }

    public double getPriceForAllProducts() {
        return products.stream().map(item -> item.price).reduce(0.0, Double::sum);
    }

    public String calculateProductSumForOnePerson(int personCount) {
        return Formatter.formatSum(getPriceForAllProducts()/personCount);
    }
}