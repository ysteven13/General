// Eclipse
// workspace: any folder containing all the settings/projects you use
//   projects: folders with files/settings/subfolders for programs/classes
//      code that is supposed to work together
//packages:(actual java)
//     multiple programs: different programs can be within one project
// user interface:
//   "perspectives" - diff ways of looking at the same code
//     windows

// Standard Java: everything is class-based
// a program is a class!
// a class with a main() function is a program


public class Feb12_CS02 {
//think of main() like the PRocessing Setup function
	public static void main(String[] args) {
		countdown(3);//printout 3,2,1,0 on different lines
		System.out.println("Hello; world");
		System.out.println("woah");
		System.out.println(5+23*12*124);
		// the way classes and . operator works
		//System class is used for interacting with the OS/Eclipse
		//variable called out within System that is used for output.
		//within that variable is called println
		//we always need to contain the functions within a class
		
		//Public: access modifier  -  anyone can access our class/main function
		//static means that we can call the main function without saying new  Feb12()
		//before, we needed to create a new instance to call functions
		//the class is simply a container for a function that works on its own
		//Math.min is a static function - no need to say "new Math()"
		//

	}//END MAIN
	static void countdown(int x) {// we use static so we don't need to create the feb 12
		//recursion -  a function that calls itself
		if (x<0) {
			return;
		}
		
		System.out.println(x);
		 countdown(x-1);
		
	}
	

}//END Feb12_CS02

// print out 3   2   1   0
//  countdown(2)
//     countdown(1)
//        countdown(0)
//           countdown(-1)
//            (base case)




