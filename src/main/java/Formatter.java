import static java.lang.Math.floor;

public class Formatter {
    static String formatSum(double sum) {
        int intSum = (int) floor(sum);

        String currency = switch ((intSum <= 20) ? intSum : (intSum % 10)) {
            case 1 -> "рубль";
            case 2, 3, 4 -> "рубля";
            default -> "рублей";
        };

        return String.format("%.2f", sum) + " " + currency;
    }
}