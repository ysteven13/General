
//Binary tree(at most 2 children each)
//Each element is ordered in relation to its parent/ancestors by ordering rule
//every child data must be less than the data in the parent/ancestors
// red-black trees are BSTs that color their nodes to maintain good balance

public class CS02_Fri_March_27 {

	public static void main(String[] args) {
		Tree good = new Tree();
		int i = 0;
		good.bstAdd(30);
		good.bstAdd(10);
		good.bstAdd(60);
		good.bstAdd(20);
		good.bstAdd(70);
		System.out.println(good.bstSearch(60));
		System.out.println(good.bstSearch(70));
		System.out.println(good.bstSearch(5));

		// TODO Auto-generated method stub

	}

	// write a node class definition with int data
	static class Tree {
		Node root;

		boolean bstSearch(int data) {
			Node n = root;
			while (n != null) {
				System.out.println("searching: " + n.data);
				if (n.data == data) {
					return true;
				}
				if (data < n.data) {
					n = n.left;
				} else {
					n = n.right;
				}
			}
			return false;
		}

		void bstAdd(int data) {
			Node n = new Node(data);
			if (root == null) {
				root = n;
				return;
			} else {
				Node curr = root;
				while (true) {
					if (n.data <= curr.data) {
						if (curr.left == null) {
							curr.left = n;
							break;
						} else {
							curr = curr.left;
						}
					} else {
						if (curr.right == null) {
							curr.right = n;
							break;
						} else {
							curr = curr.right;
						}
					}
				}
			}
		}
	}

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}

	}

}
