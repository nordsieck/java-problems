import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class GCJSavingTheUniverseTest {
    @Test
    public void testSwitches() {
	String[][] engines = {
	    {"Yeehaw", "NSM", "Dont Ask", "B9", "Googol"},
	    {"Yeehaw", "NSM", "Dont Ask", "B9", "Googol"},
	};
	String[][] queries = {
	    {"Yeehaw", "Yeehaw", "Googol", "B9", "Googol", "NSM", "B9", "NSM", "Dont Ask", "Googol"},
	    {"Googol", "Dont Ask", "NSM", "NSM", "Yeehaw", "Yeehaw", "Googol"},
	};
	int[] switches = {1, 0};

	for (int i = 0; i < engines.length; ++i) {
	    GCJSavingTheUniverse stu = new GCJSavingTheUniverse(engines[i]);
	    int s = stu.CountSwitches(queries[i]);
	    Assertions.assertEquals(switches[i], s);
	}
    }

    @Test
    public void testProcess() {
	String in = "2\n" +
	    "5\n" + "Yeehaw\n" + "NSM\n" + "Dont Ask\n" + "B9\n" + "Googol\n" +
	    "10\n" + "Yeehaw\n"+ "Yeehaw\n"+ "Googol\n"+ "B9\n" + "Googol\n" +
	    "NSM\n" + "B9\n" + "NSM\n" + "Dont Ask\n" + "Googol\n" +
	    "5\n" + "Yeehaw\n" + "NSM\n" + "Dont Ask\n" + "B9\n" + "Googol\n" +
	    "7\n" + "Googol\n" + "Dont Ask\n" + "NSM\n" + "NSM\n" + "Yeehaw\n" +
	    "Yeehaw\n" + "Googol\n";

	String out = "Case #1: 1\n" + "Case #2: 0\n";

	InputStream ins = new ByteArrayInputStream(in.getBytes());
	ByteArrayOutputStream outs = new ByteArrayOutputStream();

	GCJSavingTheUniverse.Process(ins, outs);
	try {
	    String output = outs.toString("UTF-8");
	    Assertions.assertEquals(out, output);
	} catch (Exception e) { Assertions.fail(e); }
    }
}
