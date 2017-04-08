
public class CS02_BeforeBreak_4_15 {
//Topic: Linked Lists
	//A list of objects conneced or linked by references
	//it is a way of storing information in a particular format
	//similar to an array
	//these are stored in a line
	//Node: an object within a data strcture, connected to other nodes
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = null; //empty list
		//head is the beginning of the list
		head = new Node();
		head.data = 5;
		head.next = new Node();
		head.next.data = 9;
		// head -> 5 -> 9 -> null
		//if we dont change it the head.next is set to null by default
		head.next.next = new Node(0,new Node(-6, new Node(8,null)));
		printList(head);
		
		LL list = new LL(); //empty LL, head tail = null
		
		list.add(5);
		list.add(7);
		list.add(1);
		list.add(9);
		printList(list.head);
		
		
		System.out.println(list.removeFromHead().data); //5
		System.out.println(list.removeFromHead().data);//7
		System.out.println(list.removeFromHead().data);//1
		System.out.println(list.removeFromHead().data);//9
		System.out.println(list.removeFromHead());//null
	}
	
	static void printList(Node n) {
		if (n == null) {
			System.out.println("null");	
		}else {//recursive case
			System.out.print(n.data + " ->");
			printList(n.next);
		}
	}
static class Node {
	//you need the actual information
	int data; //value, elemment, info, etc. this is the list reason
	//we need to reference 
	Node next; //refers to the next value in the list
	Node() {
		//recreation of the default constructor to avoid losing it with another constructor
		
	}
	Node(int data, Node next) {
		this.data = data;
		this.next = next;
		
	}
}
	static class LL {
		Node head;  //all you really need
		Node tail; //reference to last Node before null
		//adds at the tail of the list
		
		void add(int data) {
			Node n = new Node(data, null);
			
			if(head == null) {
				head =  n;
			}
			else {
				tail.next = n;
			}
			tail = n;
		}
Node removeFromHead() {
		if (head == null) {
			return null;
		}
		Node n = head;
		head = head.next;
		if(head == null) {
			tail = null;
		}
		return n;
		}
		
	}
}

