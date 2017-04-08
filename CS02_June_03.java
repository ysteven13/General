
public class CS02_June_03 {
	public static void main(String[] args) {
	//done with trees. Now we are doing Java class structure
	//Inheritance: relationships between classes
		Rect r = new Rect(2.0f, 4.0f );//f in a numeric literal makes it a float "l" is a long type
		System.out.println(r.area() );
		Square s = new Square(4.0f);
		System.out.println( s.area() );
		s.setArea(25.0f);
		System.out.println(s.length + "x" + s.width);
		System.out.println( r.perimeter() );
	    System.out.println( s.perimeter() );
	    Rect r2 = new Square(9.0f);
	    System.out.println(r2.perimeter());
	    //polymorphism many shapes
	    //a subclass fits into many classes of variables
	    //any of its own class or any superclasses are applicable 
	    //java always uses the most specific possible definition when calling
	    // an object's method - Square def is more specific than Rect
	    //  r2.setArea(100.0f);  // won't work - can't c	all Square methods from Rect var
	    //Java looks at the VARIABLE type to decide whether it CAN call a function
	    //Java looks at the OBJECT type to decide HOW to run a function

	}
	static class Rect {
		float length;
		float width;//fields/instance variables
		
	Rect(float length, float width) {
		this.length = length;
		this.width = width;
	}
	//write a constructor that allows setting of both fields
		//write a method that returns the area
		float area() {
			return length*width;
		}
		float perimeter() {
			System.out.println("rectange perimeter");
			return 2*length + 2* width;
		
		}
	}
	//square is an extension of Rect
	//square is a specific kind of Rects
	static class Square extends Rect{
		// in Java, a subclass (Square) extending a superclass (Rect) inherits
	    //   the instance variables and methods of that class
	    // another way: the child class (subclass) inherits from parent (superclass)
		//square already has .length and .width and .area()
		//if we provide no explicit constructor, java gives the default constructor impliicitly
		//Square() {
			//super();//super keyword gives access to constructors and methods of superclass equiv: Rect()
			//any constructor makes a call to superclass constructor on its first line
			// implicitly if you dont do it explicitly
			//but rect requires an imput
		Square(float side) {
			super(side, side);//this is Rect(side,side) MUST BE TOP LINE
			//using super to call rect constructor with two same side lengths
		}
		void setArea(float area) {
			//cannot set area for rectangle
			float side = (float) Math.sqrt(area);
			this.length = side;
			width = side;
		}
		float perimeter() {
		      System.out.println("Square perimeter");
		      return 4 * length;   // sliiiightly faster than Rect version (silly)
		    }

		
		}	
	}

