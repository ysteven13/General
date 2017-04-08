import java.util.Arrays;
public class CS02_Fri_3_18_2016 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//binary search review:
		//1,4,9,20,99,100,150,200
		//binary search is going to narrow down on this array
		// if we're looking for say 4 we would go left
		//every doubling of the input size is a new work step
		// log_2 n = how many times to divide n by 2 before hitting 1 or 0
		//log_2 8 = 3
		//log_2 16 = 4
		//O(log n) 
		//change of base log rule: log_3 n = log_2n/log_2 3
		//change rule: log_a n = log_b n / log_b a
		//log_2 3 is a constant factor (about 1.5x) - if we were splitting into 3
		//pieces instead of 2 pieces it would be l.5x less work
		///but proportionally this is still the same as O(log_2 n)
		// the difference between bases of the logarithm is not important for big-O
		int [] nums =  new int []{50,25,100,25,4,10,99};
		mergeSort(nums);
		System.out.println(Arrays.toString(nums) );

	}
	
	
	static void mergeSort(int[] a) {
		//divide and conquer: split up the array into tiny peices, sort them seperately
		 // 50, 25, 100, 25, 4, 10, 99
	    // 50, 25, 100           25, 4, 10, 99
	    // 50,     25, 100       25, 4          10, 99
	    // (50)    25,    100,   25,    4,      10,     99	-3 levels of splitting
	    // (50)    25, 100,      4, 25          10, 99		-3 levels of merging
	    // 25, 50, 100           4, 10, 25, 99
	    // 4, 10, 25, 25, 50, 99, 100
		
		//every time a number was picked only 2 different numbers were compared
		//therefore the work does not increase with the array size
		//the only thing that increases is the number of comparisons
		// we have 2 log n levels this is a constant factor
		// each leve; is O(n)
		//overall 2 log n* 2n = 4n log n = O(n log n)
		//best to worst: O(1), O(log n), O(n), O(n log n), O(n^2), O(n^3)
		
		
		//base case
		if (a.length <=1) {
			return;//nothing to sort
		}
		
		int mid = a.length/2;
		// 0 1 2 3 length 4 mid = 2 
		
		int [] left = new int[mid];
		int [] right = new int[a.length-left.length];
		for(int i= 0; i<left.length; i++) {
			left[i] = a[i];
		}
		for(int i = 0; i< right.length; i++) {
			right[i] = a[i+mid];
		}
		// now sort the side
		//now merge sort
		mergeSort(left);
		mergeSort(right);
		//after the sides are sorted we must merge the sides
		
		merge(a, left, right);
		
		
	}
	
static void merge(int[] a, int [] p, int [] q ) {
		int pi = 0; 
		int qi = 0;
		for(int i = 0; i< a.length; i++) {
			if(qi >= q.length || // if we're done with array q, auto pick from p
				//this is known as short circuiting: dont check rest of logic calc if result is alreadu known
				pi < p.length &&//if we're done with array p, dont check p.
			   p[pi] <= q[qi]) {
								//pick from p
				a[i] = p[pi];	//move to next position
				pi++;
			}
			else {
				a[i] = q[qi]; //pick from q
				qi++;
			}
		}
		
	}
	

}






