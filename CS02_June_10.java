
public class CS02_June_10 {//Interfaces Week 16 which is another form of inheritance
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle c = new Car();
		c.speed = 118;
		c.accelerate();
		System.out.println(c.speed);
		c.accelerate();
		System.out.println(c.speed);
		//just like classes, interfaces define new types(for variables, not objects)
		Metallic m =  new Car();
		if(m.isMagnetic()) {
			System.out.println("electromagnet grabbed the car. ");
		}//we cannot use car things ex. cannot accelerate
	}
	abstract static class Vehicle {
		float speed;
		float topSpeed;
		
		
		abstract float power();
		//each vehicle type can have its own power.
		//either way we can specify each number.
		//pros/cons to each one
		//variable can be changed.
		//no abstract keyword.
		//abstract method version
		//abstract methods cant be changed without using a variable.
		//however the method is abstract and can consistently have a definition for each subclass
		//perform 1 unit of acceleration
		//adding power to speed. don't exceed top speed
		void accelerate() {
			speed += power();
			speed = Math.min(topSpeed, speed);
		}
	}
	//single inheritance Car (any class) can only extend one other class
	//interfaces allow multiple inheritance.
	static class Car extends Vehicle implements Metallic{//this eays that cars are extensions of the idea of vehicle and are also metallic
		
		float power() {
			return 1.5f;//cars acceerate 1.5 mph / time unit
			
		}
		Car() {
			topSpeed = 120;//accesses the inherited topSpeed var
		}
		public boolean isMagnetic() {
			return true;
		}
	}
	
	static interface Metallic {//an interface are alot like abstract classes except they provide no vars and all methods are abstract by default
		//interfaces allow interaction
		//all interfaces are abstract and public	
			boolean isMagnetic();
			//this is implicityly abstract and must override
		}	
	  static class Sword implements Metallic, Comparable<Sword> {
		    float length;
		    String material;  // "steel" "aluminum"

		    Sword(float len, String mat) {
		      length = len;
		      material = mat;
		    }

		    // isMagnetic can be written in whatever is appropriate for its class
		    //   even if other versions work totally differently
		    public boolean isMagnetic() {
		      return "steel".equals(material);
		    }
		    public int compareTo(Sword other) {
		    	if(length < other.length) return -1;//less
		    	if(length> other.length) return 1;//greater
		    	return 0;//equal
		    	
		    	//nothing in Java forces the "negative, 0 , and positive system
		    	//its up to each class that is comparable to define the function in hat way
		    	//if it wants to be correctly compared
		    }
		    
		  }

		  static interface Comparable<T> {
		    int compareTo(T other);
		    // every thing Comparable to T has to have a compareTo method
		    // that returns a result comparing the object to the other thing
		  }
	
	
}
