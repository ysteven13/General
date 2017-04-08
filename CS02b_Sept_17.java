//Nodes - things that can be connected
		//That connection involves two values
		//They are the building blocks of data structures
		//Linked list
			//linear data structure made out of nodes.
		//trees - a tree is a set of nodes laid out with a "branching" pattern
			//based on two nodes branched from each previous level
			//not linear therefore easier to use binary search
		
public class CS02b_Sept_17 {

	public static void main(String[] args) {
		//primitive types cannot be used with generics
		//<> auto detects the type
		LNode<Integer> head = new LNode<>(8,null);//you need to be more specific using generics
		//null means "there could be a reference, but nope"
		//accessing members of a null reference will crash
		//this is because null references don't actually lead to objects
		LNode<Integer> newstuff = new LNode<>(10, new LNode<> (-3,null) );
		head.next = newstuff;//the next object in the head object is newStuff
		System.out.println(print(head));
			//newStuff is a local variable that is changing
			//this refers to the new nodes (constructor returns a ref to the new nodes)
			//head is not changing only the object referred to by head is changing
			//head reference is not changing
			//Every variable before a . is not changing the ones after are
		
		
		//newStuff should be a reference to the node with a 10
	}
	
	//Generic methods create a class variable before the return type of the method definition
	// type of method definition, which can be used any other place in the method definition
	//we can use <U> instead of <T> and it doesn't matter
	static <U> int print (LNode<U> n) {//java figures out your generic type based on function inputs
		if(n == null) {
			System.out.println("null");
			return 0;	
		} 
			System.out.println(n.value);
			return 1+print(n.next);
			//this returns length of list
			//starting one step after this one	
	}
	
	
	
	
	
	//to make a class generic, it needs a type variable 
	//after the class name: <T> or <U> or <AType> usually we use caps
	//after that whatever inside<> is the type that you use later
	//Java auto tracks the type any time you use
	
	static class LNode<T> {//this is the basis for a list
		T value; //these two instance variables are members of the LNode class
		LNode<T> next; //and any object of that class SPECIFY next LNODE is type T
		
		
		//LNode constructor is a member of the class, as are any methods
		//Constructor input must also not be a raw type must specify type T
		LNode(T value, LNode<T> next) {
			this.value =  value;
			this.next = next;
		}
		
	}

}
