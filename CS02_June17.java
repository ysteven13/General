
public class CS02_June17 {
	
	//static means that a class member can be used without any objects
	//static means that one instance of that class member exists
	//regardless of the number of objects of that class
	// static members are associated with the class itself, not any
	//particular object
	//all objects share a single copy of static members
	 public static void main(String[] args) {//public class members can be accessed by ANYONE
		 Pnt.pc();
		 // we access static members from outside the class definition
		    // using the name of the class itself (Math.sqrt)

		 
		 //interface methods are auto-public
		 Pnt p1 = new Pnt(3, 7);
		 Pnt p2 = new Pnt(-4, 2);
		 Pnt p3 = new Pnt(3, 7);
		 p2.x = -6;
		 //local variables have no concept of visibility or access level
		 //they are only visible inside their function
		 //System.out.println(p2.y);
		 //we have access to x from outside the class def
		 //we can no longer "see" the y variable from the outside Pnt
		 //class definition
		 System.out.println(p1);
		 System.out.println(p1.equals(p3));//identical coordinates but seperate objects
//but java does not recognize coords just whether they are the same objects
	  } 
	 static class SomeClass {
		 
	 }

}

//every class extends Object implicitly or through a parent
//https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html


class Pnt {
	//name is chosen to avoid interfereing with other names
	 //this is completely different from the June17th class
	 int x;
	 //members that are unmarked are "package-private" by default
	 //anything in the same package can access pack-prv members
	 
	 //private variables keep people from screwing up your objects
	 //it forces them to use methods/ constructors that you define
	 //to interact with data
	// common design pattern: all data private, methods public/private/
	  //   etc as desired

	// any private member (field, method, inner class) of a class
	  //   cannot be directly accessed from outside that class
	  //   not even by subclasses

	 private int y;//anything marked as private can't be accessed
	 //public does the opposite.
	 
	 //"protected" is the 4th visility
	 //it allows code in the same package OR any subclasses(even in diff package)
	 static int counter = 0;//this is shared across all Pnt objects
	  Pnt(int x, int y) {
		 this.x = x;
		 this.y = y;
		 counter++;
		  // in non-static methods/constructors, we have direct access
		    // to instance members of this object, AND static members
		    // of this class

	 }
	  static void pc() {
		  System.out.println(counter);
		  //static methods can access static class variables but not
		  //any instance variables
	  }
	  public String toString() {//changes the definition and also has to be public.
		  return "(" + x + "," + y + ")";
	  }
	  public boolean equals(Object other) {
		  if(!(other instanceof Pnt)) {
			  //istance of is an operator that evaluates to true
			  //if the specified object is an instance of that class
			  //built in null check
			  return false;
		  }
		  Pnt p = (Pnt) other;
		  //typecast other to a Pnt, which could crash if Pnt wasnt really a point,
		  //must be very careful
		  
		  return this.x == p.x && this.y == p.y;
		  //privacy affects access to variables from inside/outside
		  //class definitions and between packages
		  //NOT two objects of the same type
	  }
  }



