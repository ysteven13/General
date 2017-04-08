import java.util.Arrays;

public class CS02_Fri_March_11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 int [] nums = new int []{4,4,5,9,10,12,16,22};
 //System.out.println(insertionSort(nums));
 insertionSort(nums);//do reverse insertion sort

 System.out.println( Arrays.toString(nums));
 
 
 nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
 System.out.println( insertionSort(nums) );
 System.out.println( Arrays.toString(nums) );
 nums = new int[]  {22, 16, 12, 10, 9, 5, 4, 3};
 
 System.out.println(seq(nums,12));  // 2
 System.out.println( seq(nums, 4) ); //6
 System.out.println( seq(nums, 14) ); // returns -1 (not found)
 
 
 System.out.println( bin(nums, 12) );   // 2
 System.out.println( bin(nums, 4) );    // 6
 System.out.println( bin(nums, 14) );   // -1    (not found)

	}
	
	static int bin(int[] a, int searchVal) {
		//in binary search the idea is to divide and conquer 
		//try to eliminate Half of the possibilities with every step
		//22,16,12,10,9,5,4 - ex: searchVal = 14
		//			^ 14 is bigger than 10 so we limit to 22,16,12
		//then we look at the middle of 22,16,12 and 14 is less than 16 so it is to the right
		//so 12 is the only possibility left
		// 14 is greater than 12 to it is to the left of 12
		//look to right of 16 to left of 12
		//but there is nothing left so return -1
		int  low = 0;
		int high =  a.length-1; //indexes of the endpoints of range of poss.
		
		while (high >= low) {
			int mid = (high + low) / 2; //index of midpoint
			if (a[mid] == searchVal) {
				return mid; //found it
			}
			if(searchVal >a[mid]) {
				high = mid -1; // don't look as far right anymore
			}
			else {//value is smaller, to right
				low = mid+1;//don't look as far left anymore
			}
		}
		return -1;
		
		
		
	}
	
	// O(n) loop is not that good for search
	static int seq(int [] a, int searchVal) {
		for (int i = 0 ; i <a.length; i++) {
			if (a[i] == searchVal) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	
	
	
	 // two ways to reverse sort order:
    // reverse loop direction - insert small numbers to right side
    // reverse comparison operations - inserting big numbers to left side


	static int insertionSort(int [] a) {
		//in combining code O(n) * O(n) * O(n) = O(n^2)
		//therefore this is called an O(n)^2
		// the amount of work is proportional to n^2
		// if we double the amount of input we quadruple the amount of work
		
		
		
		int count = 0;
		for (int i = a.length-2; i >= 0; i--) {  // right to left
		      // at each element, insert it back in the direction we came from, sorted

		      // j should follow the element that we are inserting back to the right
		      for (int j = i; j < a.length-1 && a[j] < a[j+1]; j++) {
		        int temp = a[j];
		        a[j] = a[j+1];
		        a[j+1] = temp;

		        count++;
		      }
		    }

		    return count;

	}
		
		 // outer loop - n-1 repetitions for this loop - O(n) loop
		// the loop repeats a number of times proportional to n
		// if we double the size of the input(n) then we double the work we need to do
		// the inner loop however functions differently
		//there are ,multiple possibilities because it changes based on the value of i in the previous loop
		//when the start pt is close to the end pt, only runs 1 time
		//when the start pt is far from the end pt, repeat 
		//avg n/2 assuming every element inserted has to go all the way back to the end
		//this is a worst case scenario but the average case is a bit better
		//with average numbers, they'd actually need to travel n/4 spaces
		//ex. for 16 in [4, 20, 5, 9, 4, 10, 16, 12] we'd need to move n/4 elements
		// there would be n/4 different swaps but is STILL O(n) n/4 is still proportional to the size of n
		//big-O is about growth rates and not absolute measurements
		// the swapping part of his is O(1) -  constant time - it does not grow based on input
		//computers are so fast that we only need to care about efficiency of the whole function
		//it matters only as we get bigger and bigger
		
		// 4, 1, 5, 9, 4, 10, 16, 12
		// ^skip
//		    ^insert-------------^
		
		
	}
	//do insertion sort but in descending order and in 1 function
	
	
	
	
	
	
	
	
	


