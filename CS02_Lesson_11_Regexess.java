import java.util.regex.*;
public class CS02_Lesson_11_Regexess {
	// Regex: Regular expression
	// a way of describing patterns in text, can be used for searching/matching
	//imports Pattern and Matcher
	// example:
    // aaaaab
    // aaaaaaaaaaab
	 // "some amount of a's followed by one b"
    // b   <-- allowed


    // a*b is the regex for this * is amodifier saying "zero or more of the previous item"
	//b has no modifier so appears as is
	// Java regexes:
    // String pattern -> Pattern -> Matcher -> find/matching/specific pieces
    //                test String -/
	public static void main(String[] args) {
		Pattern p = Pattern.compile("a*b");
		Matcher m = p.matcher("aaaab");
		System.out.println(m.matches()); //true or false
		System.out.println(p.matcher("b").matches());
	    System.out.println(p.matcher("aaaaaabb").matches());
	    System.out.println(p.matcher("baaaaa").matches());
	    System.out.println(p.matcher("caaaaab").matches());
	    
	    m = p.matcher("aab   bdagaaaaabbbaaaacaaaab");
	    //.matches() would be false becauase the full string has other stuff other
	    //than the paterb
	    System.out.println(m.find());//it did find a pattern inthe test string
	    System.out.println(m.group());  //
	    while (m.find()) {
	        // keeps finding as long as .find is true
	        System.out.println(m.group());

	    }
	    //the last .find returned a result of false otherwise the loop would still be going
	    // m.group();
	    //calling .group with no results found gives IllegalStateException
	    // many queries to make based on current state (see docs)
	    // let's say we want to match: \blah
	    //the pattern would need a double \
	    //pattern: \\\w (if we want to allow #s and _s in command)
	    //pattern: \\[a-zA-Z] (allows both upper and lower)
	    // - allow ranges
	    //pattern: \\[a-zA-Z_] to allow everything including underscores just stick things in there
	    // String: "\\\\[a-zA-Z_]"
	    // note backslashes have been escaped at 2 levels
	    System.out.println(Pattern.compile("\\\\[a-zA-Z_]+").matcher("\\blah").matches());

	    //pattern: decimal number require a decimal point
	    //valid: "12.6", ".7101"
	    // invalid: "12", "61."
	    // \d to describe any decimal digit
	    // . is a special character in regular expressions (litteral . needs escape)
	    // Pattern: \d*\.\d+
	    //String: "\\d*\\.\\d+"
	 // when modifier is applied to a class, when it repeats in a test string,
	    //   each repetition can be a different member of the class

	    //p = Pattern.compile("\\d*\\.\\d+");
	    System.out.println(p.matcher("12.6").matches());
	    System.out.println(p.matcher(".7101").matches());
	    System.out.println(p.matcher("90").matches());
	    System.out.println(p.matcher("88.").matches());
	    // all common modifiers:
	    // * (0 or more)   + (1 or more)   ? (0 or 1)
	    // {4}  (4 times)   {3,6}   (b/w 3 and 6 inclusive)    {5,}  (5 or more)
	    p = Pattern.compile("([A-Z][a-z]*)( [A-Z][a-z]*)+");
	 // putting () around a section does 2 things:
	    //   allows you to repeat section (as with surnames)
	    //   //   forms a "capture group" we can retrieve later after things are matched
	    m = p.matcher("Andrew Bernon Tourtellot");
	    if (m.matches()) {
	        System.out.println(m.group());  // full match
	        System.out.println(m.group(0)); // also full match
	        System.out.println(m.group(1)); // 1st set of parentheses
	        System.out.println(m.group(2)); // last match made (in case of repetition)
	                                        //   w/in 2nd set of parens

	      }



	  }




	    


	}


