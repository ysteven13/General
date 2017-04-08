
import java.util.Arrays;

public class CS02_Fri_Feb_26 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int[] nums = new int [] {20,4,1,20,15};
bubbleSort(nums);

// Selection sort:
// instead of swapping adjacent #s, look for the lowest number in the array
// swap that one into place
// repeat that for next lowest #
// repeat again

System.out.println(Arrays.toString(nums) );

System.out.println(sum(nums));
}//end main
	static void bubbleSort(int [] x) {
		for(int t = 0; t<x.length; t++) {
			for (int i = 1; i< x.length; i++) {
				if(x[i-1]> x[i]) {
		
					swap(x,i-1,i);
				}
			}
			 System.out.println( Arrays.toString(x) );
		}
	}
	
	
	static void swap(int[] x,int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
		
	}
	
static int sum(int [] x) {
	return sum(x,0); //function overloading: define 2 of the same-name funcs w/ different input variables
	
	}

static int sum(int[] x, int i) {
		//sum x starting at index i
	//base case
	if(i >= x.length) {
		return 0;
		}
	//recursive step
	return x[i] + sum(x, i+1);
	}
	

}//End of class

