import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class GCJTrainTimetableTest {
    @Test
    public void testCountTrains() {
	int[][] aToB = {
	    {9, 0, 12, 0, 5},
	    {10, 0, 13, 0, 5},
	    {11, 0, 12, 30, 5},
	};
	int[][] bToA = {
	    {12, 2, 15, 0, 5},
	    {9, 0, 10, 30, 5},
	};
	GCJTrainTimetable tt = new GCJTrainTimetable(aToB, bToA);
	Assertions.assertArrayEquals(new int[]{2, 2}, tt.CountTrains());

	aToB = new int[][]{
	    {9, 0, 9, 1, 5},
	    {12, 0, 12, 2, 5},
	};
	bToA = new int[][]{};
	tt = new GCJTrainTimetable(aToB, bToA);
	Assertions.assertArrayEquals(new int[]{2, 0}, tt.CountTrains());
    }

    @Test
    public void testProcess() {
	String in = "2\n" + "5\n" + "3 2\n" +
	    "09:00 12:00\n" +
	    "10:00 13:00\n" +
	    "11:00 12:30\n" +
	    "12:02 15:00\n" +
	    "09:00 10:30\n" +
	    "2\n" + "2 0\n" +
	    "09:00 09:01\n" +
	    "12:00 12:02\n";

	String out = "Case #1: 2 2\n" + "Case #2: 2 0\n";

	InputStream ins = new ByteArrayInputStream(in.getBytes());
	ByteArrayOutputStream outs = new ByteArrayOutputStream();

	GCJTrainTimetable.Process(ins, outs);
	try {
	    String output = outs.toString("UTF-8");
	    Assertions.assertEquals(out, output);
	} catch (Exception e) { Assertions.fail(e); }
    }
}
