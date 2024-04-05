package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormat {
    public static String format(int amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(amount);
    }
}