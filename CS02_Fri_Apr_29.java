import java.util.ArrayList;

public class CS02_Fri_Apr_29 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//arrayList is a generic class- it can be used to contain any type
		ArrayList<Integer> intList = new ArrayList<Integer>();
		//to create an ArrayList object we must also specify the generic tyoe
		//for that object
		intList.add(5);	//5 is int, => Integer(5) autoboxing
		intList.add(9);	//if taken out there is autounboxing
		intList.add(-2);//java automatically does boxing and unboxing as needed
		System.out.println(intList);
		
		//generic types cannot be primitive types
		//but we can still use integer double boolean , character, etc
		//generic types can be any object tyoe
		
		ArrayList<String> names = new ArrayList<>();
		//"diamond operator" this fills in a type auto
		//"type inference" it figures out the type
		//based on the types in the rest of the statement
		names.add("andrew");
		names.add("");
		//data structure: structured data
		//Multple pieces of data together
		Node<Integer> head = new Node<>(4,new Node<>(9,new Node<>(2,null)));
		display(head);
	}//END MAIN
	
	//generic methods have type variables just before the return type
	//Java infers the type every time the function is called, based on inputs
	static <T> void display(Node<T> n) {
		if (n == null) {
			System.out.println("null");
		}
		else {
			//need to print data from this node (n), then recur on the next nodes
			System.out.print(n.data + " -> ");
			display(n.next);
		}
		
	}
	
	static class Node<T> {//any variable name after brackets in classes are generic{
		T data;
		Node<T> next;
		 // substitution principle: once we have a specific Node with a specific type
	    //   mentally substitute the specific type anywhere the var appears
	 
	Node(T data, Node<T> next) {
		//whatever types are involved, assign them to this Node's variables
		this.data = data;
		this.next = next;
		//"this" is a reference to this specific Node we're creating
		
	
	}

	}

}

//generic types it is a "not specific" type
//it is not specific about what it is defining
//it lets us define a class or a funcition using a generic type
//called a generic class or function/method

