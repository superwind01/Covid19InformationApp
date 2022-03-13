package DecimalFormat;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberToDecimal {
    public static String main(double number){
        Locale locale = new Locale("en","EN");
        NumberFormat format = NumberFormat.getNumberInstance(locale);
        return format.format(number);
    }
}
