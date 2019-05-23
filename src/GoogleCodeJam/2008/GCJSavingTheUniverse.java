import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Integer;
import java.lang.System;

public class GCJSavingTheUniverse {
    public String[] engines;
    
    public GCJSavingTheUniverse(String[] engines) { this.engines = engines; }

    public int CountSwitches(String[] queries) {
	int switches = 0;

	Set<String> remaining = new HashSet<String>();
	for (String e: this.engines) { remaining.add(e); }

	for (String q: queries) {
	    if (remaining.size() == 1 && remaining.contains(q)) {
		for (String e: this.engines) { remaining.add(e); }
		switches += 1;
	    }
	    remaining.remove(q);
	}
	return switches;
    }

    public static void Process(InputStream in, OutputStream out) {
    	Scanner s = new Scanner(in);
	s.useDelimiter("\n");
	
    	int cases = s.nextInt();

    	for (int i = 0; i < cases; i++) {
    	    int numEngines = s.nextInt();
    	    String[] engines = new String[numEngines];
    	    for (int j = 0; j < numEngines; j++) { engines[j] = s.next(); }

    	    int numQueries = s.nextInt();
    	    String[] queries = new String[numQueries];
    	    for (int j = 0; j < numQueries; j++) { queries[j] = s.next(); }

    	    GCJSavingTheUniverse stu = new GCJSavingTheUniverse(engines);

	    try {
		out.write(("Case #" + Integer.toString(i + 1) + ": ").getBytes());
		out.write(Integer.toString(stu.CountSwitches(queries)).getBytes());
		out.write("\n".getBytes());
	    } catch (IOException e) {}
    	}
    }

    public static void main(String[] args) { GCJSavingTheUniverse.Process(System.in, System.out); }
}
