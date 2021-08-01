import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class StringUtils {
    public static String betterString(String string1, String string2, BiPredicate<String, String> condition) {
        if (condition.test(string1, string2)) {
            return string1;
        } else {
            return string2;
        }
    }

    public static boolean checkString(String str, Predicate<Character> check) {
        for (char c: str.toCharArray()) {
            if (!check.test(c))
                return false;
        }
        return true;
    }
}
