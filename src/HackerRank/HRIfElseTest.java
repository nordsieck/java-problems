import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HRIfElseTest {
    private static String d(int n) { return HRIfElse.decide(n); }
    
    @Test
    public void testDecide() {
	Assertions.assertEquals("Weird", d(3));
	Assertions.assertEquals("Not Weird", d(2));
	Assertions.assertEquals("Weird", d(20));
	Assertions.assertEquals("Not Weird", d(22));
    }
}
