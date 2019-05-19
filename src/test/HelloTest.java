import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloTest {

    @Test
    public void testSay() {
	Assertions.assertEquals("hi", Hello.say());
    }
}
