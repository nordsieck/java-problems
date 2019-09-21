import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class GCJMinimumScalarProductTest {
    @Test
    public void testCalculate() {
	long[][][] cases = new long[][][]{
	    {{1, 3, -5}, {-2, 4, 1}},
	    {{1, 2, 3, 4, 5}, {1, 0, 1, 0, 1}},
	};

	int[] results = new int[]{-25, 6};

	for (int c = 0; c < cases.length; c++) {
	    Assertions.assertEquals(GCJMinimumScalarProduct.Calculate(cases[c][0], cases[c][1]), results[c]);
	}
    }

    @Test
    public void testProcess() {
	String in = "2\n" + "3\n" + "1 3 -5\n" +
	    "-2 4 1\n" + "5\n" + "1 2 3 4 5\n" +
	    "1 0 1 0 1\n";

	String out = "Case #1: -25\n" + "Case #2: 6\n";

	InputStream ins = new ByteArrayInputStream(in.getBytes());
	ByteArrayOutputStream outs = new ByteArrayOutputStream();

	GCJMinimumScalarProduct.Process(ins, outs);
	try {
	    String output = outs.toString("UTF-8");
	    Assertions.assertEquals(out, output);
	} catch (Exception e) { Assertions.fail(e); }
    }
}
