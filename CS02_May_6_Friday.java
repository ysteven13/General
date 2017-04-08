
public class CS02_May_6_Friday {
	public static void main(String[] args) {
		//Review: Linked Lists and Generics quiz next class. Oh no
		node<Integer> head = new node<>(3, new node<>(5,new node<>(9,null))) ; 
		displayList(head);
		 displayList2(head);
		 node<Integer> head2 = head.copyList();
		 displayList(head);// 3-> 5 -> 9 -> null
		 head2.data++;
		 removeLast(head);
		 displayList(head2);
		 displayList(head);
	}//END MAIN
	
	static <T> void removeLast(node<T> n) {//NOT SAFE IF LIST IS SHORT!!
		while (n.next.next != null) {
			n = n.next;
		}
		n.next = n.next.next;//2nd to last node refers to "null" after the last node
	}
	static <T> void displayList(node<T> n) {
		
		if( n == null) {
			System.out.println("null");
			return;
		}
		else {
			System.out.print(n.data + "->");
			//n=n.next;
		}
		displayList(n.next);
	}
	static class node<T> {
		T data;
		node <T> next;
		node(T data, node<T> next) {
			this.data = data;
			this.next = next;
		}
		node<T> copyList(){
		//strategy to copy this would be to make a new list and copy over we need to work with list tail
		//recursion would be much easier to do
		//copy one node then connect the copied node to the copy of the list following the node
		// Node<Integer> head2 = head.copyList();
		node<T> copy = new node<>(data, null);
	if(this.next != null) {
		copy.next = this.next.copyList();
		}
		return copy;			
		}	
	}
	static <T> void displayList2(node<T> n) {
		node<T>  curNode = n;
		while (curNode != null) {
			System.out.print(curNode.data + " ->");
			curNode = curNode.next;
		}
		System.out.print("null");
	}
	
	//create a generic Node class with a constructor that sets your data, next.
	// looping version:

    // original -> 3 -> 5 -> 9 -> null
    //         this^              n^
    
    // copy ->     3 -> 5 -> 9 -> null
    //                      c^

}
