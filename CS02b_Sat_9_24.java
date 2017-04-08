import java.util.*;

public class CS02b_Sat_9_24 {

	public static void main(String[] args) {
		TNode<Integer> root = new TNode<>(7, new TNode<>(9, new TNode<>(4), null),

				new TNode<>(20, new TNode<>(17, null, new TNode<>(0)),

						new TNode<>(6)));

		System.out.println("Min whole tree: " + findMin(root));
		System.out.println("Min left subtree: " + findMin(root.left));
		System.out.println("Min right subtree: " + findMin(root.right));

		// set: not-necessarily ordered collection of unique data items
		Set<String> names = new TreeSet<>();// TreeSet = self-balancing Binary
											// Search TRee
		// (red-black tree) always sorted,O(log n) for adding/lookup
		// HasSet uses a hashtable for O(1) performance (not sorted)
		// due to polymorphism both can be had
		names.add("Chi Bong");
		names.add("Ben");
		names.add("Andrew");
		System.out.println(names);
		// Map: data structure with linked key-value pairs
		// lookup is done with the key, retreives the value
		Map<Integer, String> idNames = new TreeMap<>();
		// sorted by id number because the first generic type was Integer
		// (that is the key to the data structure organization)
		idNames.put(7, "Andrew");
		idNames.put(4, "Ben");
		for (int id : idNames.keySet()) {// keyset retreives the set of keys
			System.out.println(idNames.get(id));
			// loops thru (ordered) keys and gets associated values for each

		}
		System.out.println(idNames);
	}

	static <U extends Comparable<U>> U findMin(TNode<U> node) {
		if (node == null) {
			return null;
		}
		U smallest = node.value;
		U leftVal = findMin(node.left);
		U rightVal = findMin(node.right);
		if (leftVal != null && leftVal.compareTo(smallest) < 0) {
			smallest = leftVal;
		}
		if (rightVal != null && rightVal.compareTo(smallest) < 0) {
			return rightVal;
		}

		return smallest;
	}
	// if we had a BST(binary search tree) all small nodes would be left of
	// their parents
	// big ones would be to the right. To find min value in a binary tree we
	// need recursion
	//

	// generic types: <U> after class name or before method return type
	// to make that class/method generic

	static class TNode<U> {
		U value;
		TNode<U> left;
		TNode<U> right;

		// write 2 constructors 1 to create a node with value, no children
		// another with all fields filled in
		// field is instance variable
		TNode(U value) {
			this.value = value;
		}

		TNode(U value, TNode<U> left, TNode<U> right) {
			this(value);// this calls sister constructor
			this.left = left;
			this.right = right;
		}
	}

}
// top node of a tree is called the root of the tree
// that is where all the others grow from
// everything else is a leaf as they have no children
// things inbetween are both parents and children
// size of a tree is based off of how many levels a tree has
//