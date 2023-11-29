import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static final String ERROR_TEXT = "Что-то не так. Введите число человек, на которых будем делить счет. Число должно быть целым, больше 1.";

    public static void main(String[] args) {
        System.out.println("На скольких человек будем делить счет?");

        int count = receivePeopleCount();

        System.out.println("Отлично. Счет делим на " + count);

        var calculator = new Calculator();
        calculator.parseNewProducts();

        System.out.println("Итого, добавленные товары:");
        calculator.products.forEach(product -> System.out.println(product.name));

        System.out.println("Сумма на человека:");
        System.out.println(calculator.calculateProductSumForOnePerson(count));

        scanner.close();
    }

    static int receivePeopleCount() {
        int count;
        while (true) {
            try {
                count = scanner.nextInt();
                if (count > 1) break;
                else System.out.println(ERROR_TEXT);
            } catch (Exception __) {
                System.out.println(ERROR_TEXT);
                scanner.nextLine();
            }
        }

        scanner.nextLine();

        return count;
    }
}