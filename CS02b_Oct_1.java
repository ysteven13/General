
import java.util.*;
public class CS02b_Oct_1 {
	public static void main(String[] args) {
		 // Stacks & Queues
	    // a stack of papers: put papers on top of each other
	    //   remove a paper - easiest to remove the one on top
	    // stack is a LIFO data structure - Last In, First Out

	    // function calls use a "function call stack"

	    // power(4, 0)  <-- LAST/top method call finishes FIRST
	    // power(4, 1) <-- next one returned to after we pop
	    // power(4, 2)     the top one off the stack
	    // calculate()
	    // main()  <--- bottom/FIRST method call is LAST to finish

	    // 3 operations uses with stacks:
		//push which puts items ontop of the stack
		//pop which pulls an item off the stack
		//peek checks the item on top w/o removing from the stack
		
		//Java Deque and Stack are interfaces for Stacks
		//Deque ("Double Ended Queue -  a linear structure that is a linked list or array that can be used as a stack or a queue")
		//array and linked lists can be used as a stack or a queue
		Deque<String> calls = new ArrayDeque<>();
		//this will use an array to store our Deque which we'll use as a stack
		
		calls.push("main");
		calls.push("calculate");
		calls.push("power");
		calls.push("power");
		//simulated making a bunch of calls
		
		//to simulate calls returning;
		while (!calls.isEmpty()) {
			System.out.println(calls.pop());
		}
		//Queue: FIFO first in first out
		//Use deque for Queues
		//a,b,c (a first)
		//if we remove a we get _,b,c and now it starts at B or index 2
		//to add to a queue, .add, remove uses  is .remove
		Deque<Integer> q = new LinkedList<>();
		q.add(4);
		q.add(5);
		System.out.println(q.remove());
		q.add(4);
	    System.out.println(q.remove());
	    System.out.println(q.remove());
	    
		
		
	}
}
