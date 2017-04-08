import java.util.Arrays;

public class CS02_Fri_March_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int [] nums = new int [] {100,20,200,4,20,10,6};
// O(n^2) sorts - big - O 
//this has to do with the efficiency of our algorithms
//work done is proportional to the square of the length of the array
//so sorting our 7 input array takes say .5 sec doubling the array length
//would quadruple the amount of work that needs to be done
// 1. Bubble sort
// 2.  selection sort
// 3. insertion sort(we will insert every number coming into another array)
// 3.5 insertion sort - possibly O(n) - best case scenario

//to test an insertion sort we call it on nums then print the result
insertionSort(nums);
System.out.println( Arrays.toString(nums));


	}
	static void insertionSort2(int [] a) {
		for (int start = 1; start< a.length; start++){
			//start represents where the next number to be inserted starts
			//now instead of calling insert with the new number
			for (int i = start; i>0 && a[i-1]> a[i]; i --) {
				swap(a,i,i-1);// all these funcitons can be written in multiple ways
			}
		}		
	}
static void insertionSort(int[] a) {
for(int i = 1; i< a.length; i++) {
	insert(a,i);//inserting at a,i and now needs the insert function
	
	
}
}
static void insert(int [] a, int startIndex ) {
int i =startIndex;//I  needs to change to follow the element
while ( i> 0 && a[i-1] > a[i]) {//need to add the i >0 to guarantee against problems
	swap(a,i-1,i);
	i--;//we need to keep checking
}
}

static void swap(int [] array,int i,int j) {
	int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}
}

// {100, 20, 200, 4, 20, 10, 6}
//       ^
// {20, 100, 200, 4, 20, 10, 6}    20 needed 1 swap to get in pos on left
//           ^
// {20, 100, 200, 4, 20, 10, 6}    200 needed no swaps to get in pos
//                ^
// {4, 20, 100, 200, 20, 10, 6}    needed to make 3 swaps to get 4 inserted
 // {4, 20, 20, 100, 200, 10, 6}
//                       ^
// {4, 10, 20, 20, 100, 200, 6}
//                           ^
// {4, 6, 10, 20, 20, 100, 200}
//to insert to the left there is nowhere for the 100 do go
//the number 20 is the first one to insert
//it shifts over and we need 1 swap to get to 20
//then we go to 200 to get that into position
//but we still need to check just in case
//with the first 3 rows it looks alot like bubble sort
//but now we slide 4 all the way to the left side.
// {20, 100, 200, 4, 20, 10, 6} 
//we are inserting the number into its correct spot insertion sort moves and moves backwards then moves again






