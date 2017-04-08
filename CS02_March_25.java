import java.util.Arrays;
public class CS02_March_25 {
	//No binary search or general recursion on the quiz
	// 1. be able to write a clear precise outline in english / pseudocode
	// 2. practice writing code for algorithm
	// 3. try sorting by hand

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {5,20,4,3,99,50,40,10,20};
		System.out.println(Arrays.toString(nums));
		quickSort(nums,0,nums.length-1);
		System.out.println(Arrays.toString(nums));
		
	}
//static void quickSort(int[] a) {
//	quickSort(a,0,a.length-1); 
//	
//}
//overloaded function - 2nd def with diff types/# indexes
// sort a starting at left index thru right index, inclusive
	// messy room: clothes and papers everywhere
    // quick sort throws one type of thing to one side, just like throwing
    //   all your clothing into one corner
    // go back to one pile, and divide into separate piles, etc
    // quicksort throws all small #s to the left, so only big #s are on right
    // (then you quicksort the sections)

    // TO QUICKSORT an array section:
    // if section is size 0 or 1, stop

    // pick a pivot, using the rightmost element of the section
    //   (pivot will be used to divide array into categories: small vs large)
    // track where leftmost element is that isn't known to be small
    //   (this index divides left side from the right side)
    // loop through section left to right, up to, but not incl pivot:
      // if the element is small relative to the pivot
        // swap it with leftmost big element on the left side
        // track that the next leftmost big element is one space over
        //  (moving the partition to right to make room for the element swapped)
    // swap pivot with the leftmost big element

    // (section is now subdivided into: small subsection, pivot, large subsection)

    // quicksort each subsection

    // 10, 5, 8, 20, 6
    // ^             P
    // 5, 10, 8, 20, 6
    //    ^          P
    // 5, *6,* 8, 20, 10
    // *5, 6,*
    //         8, 20, 10
    //         ^      P
    //         8, 20, 10     // 8 is already small, it swaps with itself, its now
    //            ^   P      // KNOWN to be left side because partition pt moves
    //         8, *10*, 20
    //         *8, 10, 20*


    // 5, 20, 4, 3, 99, 50, 40, 10, 20
    // ^                            P
    // 5,
    //    ^
    // 5, 20, 4, 3,                        // each number "swapped with itself"
    //              ^                      // to indicate it was staying left side
    // 5, 20, 4, 3, 99, 50, 40, 
    //              ^
    // 5, 20, 4, 3, 10, 50, 40, 99, 20
    //                  ^           P
    // 5, 20, 4, 3, 10, *20*, 40, 99, 50
    // small or equal <-- P --> bigger

    // quicksort is at the mercy of its pivots - doesnt always divide in half
    // avg is still about O(n log n) - same as merge sort

static void quickSort(int [] a, int left, int right) {
	if(right <= left) {
		return;
	}
	int pivot = a[right];
	int partition = left;
	for (int i = left; i<right; i++) {
		if(a[i] <= pivot) {
			swap(a,i,partition);
			partition++;
		}
	}
	
	swap(a,partition,right);
	quickSort(a,left,right);
	quickSort(a,partition+1,right);
 }
static void swap(int[] a, int i, int j) {
	int temp = a[i];
    a[i] = a[j];
    a[j] = temp;

    
}

}



