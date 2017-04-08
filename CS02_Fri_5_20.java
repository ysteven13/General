
public class CS02_Fri_5_20 {
	// Welcome back to CS02

	// https://global.gotomeeting.com/join/172623765

	// Please share screens and open Eclipse

	// topic: trees
	  // tree: data structure where each node branches out to more nodes
	  // binary trees: tree where each node has at most 2 direct children

	  public static void main(String[] args) {
	    Node<Integer> root = new Node<>(1, new Node<>(4, new Node<>(3),
	                                                     new Node<>(7)),
	                                       new Node<>(6));
	    displayTree( root );  // never going to go to a new line
	    System.out.println();   // go to a new line after the tree is displayed

	    System.out.println( maxElem( root ) );
	  }

	  static <T extends Comparable<T>> T maxElem(Node<T> n) {
	    // the bound on the generic type ensures T is comparable to other Ts
	    T guess = n.data;
	  
	    if (n.left != null) {
	      T maxLeft = maxElem(n.left);
	      if (maxLeft.compareTo(guess) > 0) {
	        // compareTo returns a positive int if the left thing > right thing
	        guess = maxLeft;
	      }
	    }

	    return guess;
	  }

	  // recursive function
	  static <T> void displayTree(Node<T> n) {
	    // base case
	    if (n == null) {
	      System.out.print("null");
	      return;   // return to prevent any further work with this "node"
	    }

	    System.out.print("{d:" + n.data + ", left:");
	    displayTree(n.left);   // disp left subtree within this tree/subtree
	    System.out.print(", right:");
	    displayTree(n.right);  // disp right subtree within this one
	    System.out.print("}");
	  }

	  // write me a generic Node class for a tree (left and right references)
	  static class Node<T> {
	    T data;
	    Node<T> left;   // object/instance variables
	    Node<T> right;

	    // overloaded constructors
	    Node(T data) {
	      this.data = data;
	      // left and right are automatically null
	    }

	    Node(T data, Node<T> left, Node<T> right) {
	      this(data);  // using "this" as a constructor call
	      this.left = left;
	      this.right = right;
	    }
	  }

	}

