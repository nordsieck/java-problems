import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.System;
import java.util.Arrays;
import java.util.Scanner;

public class GCJMinimumScalarProduct {

    public static long Calculate(long[] one, long[] two) {
	Arrays.sort(one);
	Arrays.sort(two);

	long sum = 0;
	int front_one, front_two, rear_one, rear_two;
	front_one = front_two = rear_one = rear_two = 0;
	while (front_one + rear_one < one.length) {
	    if (one[front_one] * two[two.length - 1 - rear_two] < two[front_two] * one[one.length - 1 - rear_one]) {
		sum += one[front_one] * two[two.length - 1 - rear_two];
		front_one += 1;
		rear_two += 1;
	    } else {
		sum += two[front_two] * one[one.length - 1 - rear_one];
		front_two += 1;
		rear_one += 1;
	    }
				     
	}

	return sum;
    }

    public static void main(String[] args) { Process(System.in, System.out); }
    
    public static void Process(InputStream in, OutputStream out) {
	Scanner s = new Scanner(in);

	int cases = s.nextInt();

	for (int c = 0; c < cases; c++) {
	    int count = s.nextInt();

	    long[] one = new long[count];
	    for (int i = 0; i < count; i++) { one[i] = s.nextInt(); }

	    long[] two = new long[count];
	    for (int i = 0; i < count; i++) { two[i] = s.nextInt(); }

	    try {
		out.write(("Case #" + Integer.toString(c + 1) + ": ").getBytes());
		out.write(Long.toString(Calculate(one, two)).getBytes());
		out.write(("\n").getBytes());
	    } catch (IOException e) {}
	}
    }
    
}
