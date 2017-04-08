import java.util.*;
public class CS02_Fri_Oct_22 {//Lesson05
//Strings and Characters
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "a man, a plan, a canal, panama";
		//8 different characters
		//chars in a String ae in array
		//the individual characters are stored as ASCII codes(numbers representing each character)
		// there are 128/256 ASCII codes (basic/extended)
		//to distinguish between 128 values..
		// 1 bit: 0 vs 1 this is 2 values
		//2 bits: 00 vs 01 vs 10 vs 11
		// so log base 2 of number
		
		//Unicode: includes ASCII and other values as well. usually 16 bits in Java
		//(start w/ASCII, have extra bits to be able to go even more chars)
		//print the number of bits that the String takes up; assuming 16 bit uicode chars
		System.out.println(str.length()*16);
		System.out.println(str.length());
		//can we compress this string to make it use less memory
		//we only have 8 different characters
		//we need 3 different bits to distingush between 8 chars
		System.out.println("memory(compress to 3 bits each): " + str.length()*3);
		
		//want a data structure that stores pairs of Characters and their associated bit codes
		//stored as a string rather than integer
		//HashMap or TreeMap (Map stores pairs of connected key-values)
		Map<Character, String> codes = new HashMap<>();
		//key: char being encoded
		//value: bit String representing the char
		
		//feel free to switch to TreeMap if desired
		//loop through all chars in String, if it has no code give it a code
		int nextCode =  0;//change this each time 
		// for-each loop repeats for each value of c from the array
		for(Character  c: str.toCharArray()) {
			//integer.tostring can have a radix/base, ex. base 2
			if(!codes.containsKey(c)) {
				codes.put(c,Integer.toString(nextCode,2));
				nextCode++;
			}
		}
		
		System.out.println(codes);
		int numBits = Integer.toString(nextCode-1,2).length();
		System.out.println(codes.size() +  " different characters");
		System.out.println("memory(compress to "+numBits+" bits each): " + str.length()*3);
		
		StringBuilder sb = new StringBuilder();
		//efficiently builds Strings piece by piece
		sb.append("");//adds that String to the StringBuilder
		//use sb and the codes map to build the encoded version of the str
		 for (Character c : str.toCharArray()) {
		      String code = zeroPad(codes.get(c),numBits);
		      // gets value in map (code) and pads it out to right # bits
		      sb.append(code);
		    }

		 System.out.println(sb.toString());
	}
	static String zeroPad(String orig, int bits) {
		//ex: zeroPad "101",5 -> "00101"
		StringBuilder sb =  new StringBuilder();
		int padLength =  bits - orig.length();
		for(int i = 0; i < padLength; i ++) {
			sb.append("0");
		}
		sb.append(orig);
		return sb.toString();
	}
	

}
