
public class CS02_Fri_4_8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//defining a class defines a new type in java
		//
		Point p1 = new Point();
		//type: pount, name p1
		//in order to set x and y, use the . operator
		p1.x = 50;
		p1.y = 100;
		//any instance of a class type is an object
		//an object is a structured, complex piece of memory with(potentially) multiple pieces of info inside
		//java stores references to objects, not the objects directly.
	//primitive types are not objects
		//integer numbers: int, long, short, byte
		//decimals: float, double
		//true/false: boolean
		
		System.out.println(p1);
		//when we print an object we see a hashCode that 
		//is a unique code that identifies the object
		//hashCode identifies an object based on its memory location
		//you can change this behavior by defining a toString function in your class
		//
	int [] nums = new int [] {2,4,8} ;
	System.out.println(nums);
	// arrays are the other type of reference type - class objects & arrays
	  int[] other = new int[]{2, 4, 8};
	    System.out.println( other );
	   
	    System.out.println( nums == other ); //false
//actually compares the identities and not the values
	    
	    Point p2 = new Point(50,100);
	    System.out.println(p1 == p2);
	    
	    
	    
	    Point p3 = p1;//two different names for the same object
	p3.x = -10;
	System.out.println(p1.x);
	//changing one var refers to object of another
	
	p1 = null ; //p1 now refers to nothing at all
	//saying = assignment opwerator on a reference type variable only changes references
	//it doesn't change information in the object istelf (or any related vars)
	//p3 still exists
	// p1.x would crash as we will get a null pointer exception
	
	//don't follow a non existent reference, such as from "null"
	//that includes both variables and functions or methods
	if (p1 == null) {
		System.out.println(p1);
	}
	}
	
	//class definition is a blueprint for what individual
	//instances of that thing will have
static class Point {
	
	//constructor -block of code to create a new object
	//every constructor can be different for each new object
	//if you dont define a constructor, Java creates a default 
	//constructor for you that looks like this
	Point() {
		
	}
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;//thus operator specifies this one
		//gives more specific instructions to java
		
	}
	int x, y;
	
	//when we call toString it is attached to a specific point
	public String toString() {
		return "(" + x +  "," +y+")";
		//in this function Java knows to look at one partcular instance of this class
		
	}
	
}
}
