import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HRIfElse {
    public static String decide(int n) {
	if (n % 2 == 1) { return "Weird"; }
	if (n >= 2 && n <= 5) { return "Not Weird"; }
	if (n >= 6 && n <= 20) { return "Weird"; }
	if (n > 20) { return "Not Weird"; }
	return "Error";
    }
}
