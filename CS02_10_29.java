import java.util.*;
public class CS02_10_29 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String message = "a man, a plan, a canal, panama";
		//length = 30
		//bits = 30*16 using unicode 16 = 480 bits
		//30*3 using customized bit codes of size 3 = 90 bits
		//wasteful to use 3 bits for a and l when a occirs so many times and l doesn't occur
		//much at all
		
		//frequency map
		// frequency map: tell us, for any character (key)
	    //    how many times does it occur (value)
	    // 'a' => 10
	    // ' ' => 6
		Map<Character, Integer> freqs = new TreeMap<>();
				 // TreeMap, HashMap
			    // TreeMap is better because it's sorted by key - O(log n)
			    // HashMap is better because it's faster - O(1)
		for(char c: message.toCharArray()) {
			//loops through each character in message (in order)
			Integer count = freqs.get(c);//Integer with cap I is an object and can hold a null
			//.get returns null when the key isn't in map
			if(count == null) {
				count = 0;
			}
			freqs.put(c, count+1);
		}
		int totalBits = 0;
		for (int i = 0; i < message.length(); i++) {
		      int count = freqs.get(message.charAt(i));
		      if (count >= 6)      totalBits += 2;
		      else if (count >= 3) totalBits += 3;
		      else                 totalBits += 4;
		    }
		System.out.println(totalBits);
		System.out.println(freqs);
		
		
	}

}
