import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GCJTrainTimetable {
    public class Trip implements Comparable<Trip>{
	public LocalTime start;
	public LocalTime end;

	public Trip(int startHour, int startMinute, int endHour, int endMinute, int turnaround) {
	    this.start = LocalTime.of(startHour, startMinute);
	    this.end = LocalTime.of(endHour, endMinute).plusMinutes(turnaround);
	}

	public int compareTo(Trip t) {
	    if (this.start.isBefore(t.start)) { return -1; }
	    if (this.start.isAfter(t.start)) { return 1; }
	    return 0;
	}

	public String toString() { return "(" + this.start.toString() + " " + this.end.toString() + ")"; }
    }

    private PriorityQueue<Trip> aDeparting = new PriorityQueue<>();
    private PriorityQueue<Trip> bDeparting = new PriorityQueue<>();
    private PriorityQueue<LocalTime> aArriving = new PriorityQueue<>();
    private PriorityQueue<LocalTime> bArriving = new PriorityQueue<>();

    public GCJTrainTimetable(int[][] aToB, int[][] bToA) {
	for (int[] i : aToB) {
	    Trip t = new Trip(i[0], i[1], i[2], i[3], i[4]);
	    aDeparting.add(t);
	}
	for (int[] i : bToA) {
	    Trip t = new Trip(i[0], i[1], i[2], i[3], i[4]);
	    bDeparting.add(t);
	}
    }

    public int[] CountTrains() {
	int aTrainsCount = 0;
	int bTrainsCount = 0;
	int aTrainsAvailable = 0;
	int bTrainsAvailable = 0;
	
	while (aDeparting.size() != 0 || bDeparting.size() != 0) {
	    LocalTime earliest = LocalTime.MAX;

	    if (aDeparting.size() != 0 && aDeparting.peek().start.isBefore(earliest)) { earliest = aDeparting.peek().start; }
	    if (bDeparting.size() != 0 && bDeparting.peek().start.isBefore(earliest)) { earliest = bDeparting.peek().start; }
	    if (aArriving.size() != 0 && aArriving.peek().isBefore(earliest)) { earliest = aArriving.peek(); }
	    if (bArriving.size() != 0 && bArriving.peek().isBefore(earliest)) { earliest = bArriving.peek(); }

	    if (aArriving.size() != 0 && aArriving.peek().equals(earliest)) { aArriving.poll(); aTrainsAvailable += 1; }
	    if (bArriving.size() != 0 && bArriving.peek().equals(earliest)) { bArriving.poll(); bTrainsAvailable += 1; }
	    if (aDeparting.size() != 0 && aDeparting.peek().start.equals(earliest)) {
		if (aTrainsAvailable > 0) { aTrainsAvailable -= 1; } else { aTrainsCount += 1; }
		bArriving.add(aDeparting.poll().end);
	    }
	    if (bDeparting.size() != 0 && bDeparting.peek().start.equals(earliest)) {
		if (bTrainsAvailable > 0) { bTrainsAvailable -= 1; } else { bTrainsCount += 1; }
		aArriving.add(bDeparting.poll().end);
	    }
	}

	return new int[]{aTrainsCount, bTrainsCount};
    }

    public static void Process(InputStream in, OutputStream out) {
	Scanner s = new Scanner(in);

	int cases = s.nextInt();

	for (int c = 0; c < cases; c++) {
	    int turnaround = s.nextInt();
	    int numAToB = s.nextInt();
	    int numBToA = s.nextInt();

	    int[][] aToB = new int[numAToB][5];
	    int[][] bToA = new int[numBToA][5];

	    for (int i = 0; i < numAToB; i++) {
		try {
		    String rawStart = s.next();
		    LocalTime start = LocalTime.parse(rawStart);
		    String rawEnd = s.next();
		    LocalTime end = LocalTime.parse(rawEnd);
		    aToB[i] = new int[]{start.getHour(), start.getMinute(), end.getHour(), end.getMinute(), turnaround};
		} catch (Exception DateTimeParseException) {}
	    }
	    for (int i = 0; i < numBToA; i++) {
		try {
		    String rawStart = s.next();
		    LocalTime start = LocalTime.parse(rawStart);
		    String rawEnd = s.next();
		    LocalTime end = LocalTime.parse(rawEnd);
		    bToA[i] = new int[]{start.getHour(), start.getMinute(), end.getHour(), end.getMinute(), turnaround};
		} catch (Exception DateTimeParseException) {}
	    }

	    GCJTrainTimetable tt = new GCJTrainTimetable(aToB, bToA);
	    int[] trains = tt.CountTrains();

	    try {
		out.write(("Case #" + Integer.toString(c + 1) + ": ").getBytes());
		out.write((Integer.toString(trains[0]) + " ").getBytes());
		out.write((Integer.toString(trains[1]) + "\n").getBytes());
	    } catch (IOException e) {}
	}
    }

    public static void main(String[] args) { Process(System.in, System.out); }
}
