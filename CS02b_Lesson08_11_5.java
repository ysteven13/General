
public class CS02b_Lesson08_11_5 {
 public static void main(String [] args) {
	 Node<Integer> head = new Node<>(3,new Node<>(7, new Node<>(9,null)));
	 System.out.println(head ); //calls .toString on head
	 
	 head = reverse(head); //reverse the entire list
	 System.out.println(head );
	  TNode root = new TNode(30, new TNode(18, new TNode(9), new TNode(4)),
              new TNode(12));

	  if (verify(root)) {
	      System.out.println("valid tree");
	    }
	    else {
	      System.out.println("invalid tree");
	    }

 }

 static boolean verify(TNode root) {
	 if(root == null) {
		 return true;
	 }
	 boolean verified = root.left == null && root.right == null ||
			 root.left.value + root.right.value == root.value;
	 System.out.println("Local Verify for " + root.value + ": " + verified);
	 
	 verified = verify(root.left) && verify(root.right) && verified;

	 System.out.println("Subtree verify for " + root.value + ": " + verified);


	    return verified;

 }
 
 	static <T>  Node<T> reverse(Node<T> oldHead) {
 		if(oldHead == null) return null;
 		if(oldHead.next ==null) return oldHead;
 		Node<T> revNext = reverse(oldHead.next);
 		 // the node that used to be next, is now the tail of the reversed list
 	    
 		oldHead.next.next = oldHead;
 		oldHead.next = null;
 		return revNext;
 		
 		 // 3 -> (7 -> 9 -> ) null
 	    // 9 -> 7 -> 3 -> null

   }
 	  static class TNode {
 		    int value;
 		    TNode left;
 		    TNode right;


 		    TNode(int v) {
 		      value = v;
 		    }


 		    TNode(int v, TNode left, TNode right) {
 		      value = v;
 		      this.left = left;
 		      this.right = right;
 		    }
 		  }
 static class Node<T> {
	 T value;
	 Node<T> next;
	 	
	 	Node (T value, Node<T> next) {
	 		this.value = value;
	 		this.next = next;
	 		
	 	}
	 	//inherited from Object
	 public String toString() {
		 
		 
		 return value + " -> " + next;
	 }//concatenation also calls toString automatically
	 
	// the fact that concatenation calls same method makes this recursive


     // 3 -> next.toString()
     //      7 -> next.toString()
     //           9 -> null
     // 3 -> 7 -> 9 -> null

 }
}
