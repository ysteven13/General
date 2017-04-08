
import java.util.*;
public class CS02b_Oct_8 {

	public static void main(String[] args) {
		//Priotity Queue: queues items in order but also bumps higher-priority items to top
		
		//Deque<SensorReading> readings = new ArrayDeque<>();
		PriorityQueue<SensorReading> readings = new PriorityQueue<>();
		readings.add(new SensorReading("Pressure: 4",true));
		readings.add(new SensorReading("Pressure: 7",false));
		readings.add(new SensorReading("Pressure: 3",true));
		readings.add(new SensorReading("climber detected",false));
		readings.add(new SensorReading("Pressure: 4",true));
		readings.add(new SensorReading("dam broken in sec 2",false));

		//write a loop to go through the queue and display the messages
		while (!readings.isEmpty()) {
		    // .add   .remove   .size   .isEmpty
			SensorReading nextReading = readings.remove();
		    System.out.println(nextReading);
		   // System.out.println(readings.poll().toString());
		    

		}
		
		//a complete binary tree can be stored as an array
		//we want a systematic method for labeling the peicees of a tree
		
		// TODO Auto-generated method stub

	}
	//static
	static class SensorReading implements Comparable<SensorReading>{//using the comparable interface
		String message;
		boolean good;
		
		SensorReading(String msg, boolean good) {
			message = msg;
			this.good = good;
		}
		//override toString to make this print nicely
		public String toString() {
			return (good ? ":D" : "8X" +  "-" + message);
		}
		//priority queues treat smaller as higher priority
		//return lower number if higher priority is desired
		public int compareTo(SensorReading other) {
			if(good && !other.good) return 1;//if this is good other is bad other has lower ranking
			if(!good && other.good) return -1; 
			//higher priority lower return if this is worse than other
			return 0;
		}
	}

}
