import java.util.*;
import java.io.*;

/**
 * Huffman Tree builder and tester. Three files may be specified to read
 * to build a tree from, encode, and decode (build file and encode file may
 * be the same). Alternatively, a standard tree may be used.
 * 
 * Three file names are built in, but any files may be used if desired.
 */
public class HuffmanTree {
  //===========\\
  // CONSTANTS \\
  //===========\\
  
  // Note that these static fields (class variables) are final, so they
  // cannot be changed during program execution.
  
  // Literal file names. Changes not recommended.
  final static String CAGED_BIRD_F = "cagedBirdPassage.txt";
  final static String HOBBIT_F = "theHobbitPassage.txt";
  final static String MYSTERY_F = "mysteryPassage.txt";

  // Output file names. Changes not recommended.
  final static String ENCODE_OUT_F = "encoded.txt";
  final static String DECODE_OUT_F = "decoded.txt";
  
  // Tree build selections. CHANGE AS DESIRED.
  final static boolean TREE_STD = true;      // true: Use standard tree
                                              // false: Use tree made from file
  final static String TREE_F = CAGED_BIRD_F;  // File to build tree from
  
  // File selections. CHANGE AS DESIRED.
  final static String ENCODE_F = CAGED_BIRD_F; // File to encode
  final static String DECODE_F = MYSTERY_F;    // File to decode
  // (You may want DECODE_F = ENCODE_OUT_F to decode what was just encoded.)
  
  /**
   * Tree output format. CHANGE AS DESIRED.
   * true:  Display tree with all bits shown before each leaf
   * false: Only show each "branch bit" once to illustrate branching structure
   */
  final static boolean DISP_ALL_BITS = true;
  
  /**
   * Tree build option. CHANGE AS DESIRED.
   * true:  "Fill in gaps" in character map for a more versatile tree
   * false: Build tree that ONLY encodes characters found in source text
   * 
   * Note that until you complete the appropriate function, the program will
   * always act as if this were false.
   */
  final static boolean FILL_GAPS = true;
  
  /**
   * Bit String representation of "standard" tree. DO NOT CHANGE.
   * This bit String was generated from "cagedBirdPassage.txt" with 
   * "gaps" in the character frequency map filled in. 
   */
  final static String STD_TREE_BIT_STR =
    "000010110100000101111001101110000010110001000000000100111111001010100011010101101010110100001010110000100100001101000100100111010010011101110111000110101100110101000001010100101010011000101000111010100101110010111101010000111001011011000011011011001010010111001110110111000101101100000010010100010010100110111011010000101010110011010111001000010110111110110000100000010100111010101011110110101110010111010110011110110010010111010000000000101000101101101010101001001010100001001011110001010001100010100110110100000110101001110110110101011101110001010101000101001010100100010001001001111010010000010111101010101010110100111110010110001011010010101110101101100011100100000";
  
  
  //=============\\
  // MAIN METHOD \\
  //=============\\
  
  /**
   * Driver method to build Huffman tree, encode, and decode files.
   * Tree mode and file selection is specified by class constants.
   */
  public static void main(String[] args) {
    //======================================\\
    // GENERATE TREE, STANDARD OR FROM FILE \\
    //======================================\\
    HuffmanNode root;
    if (TREE_STD) {
      System.out.println("Using standard tree.");
      root = genStdTree();
    }
    else {
      // Attempt to read characters of the file selected for tree generation.
      System.out.println("Using " + TREE_F + " for tree generation.");
      char[] treeGenChars = fileChars(TREE_F);
      if (treeGenChars == null) {
        // Fall back on "standard tree" if necessary.
        // System.err is similar to System.out but intended for error messages.
        System.err.println("Warning: Could not read file for tree generation. "
                         + "Using standard tree.");
        root = genStdTree();
      }
      else {
        // Generate tree from file.
        root = genTree(treeGenChars);
        if (root == null) {
          System.err.println("Warning: Failed to generate tree from file. " +
                             "Using standard tree.");
          root = genStdTree();
        }
      }
    }
    if (root == null) {
      System.err.println("Error: No tree generated. Program aborted.");
      return;
    }
    root.display(); // (OPTIONAL) View generated tree.
    
    // Map each character in tree to corresponding bit String representation.
    //   This will be necessary for encoding characters into bits.
    Map<Character, String> bitStrings = root.bitStrings();
//    System.out.println(bitStrings);  // (OPTIONAL) View bit String mappings.
    
    // TODO (OPTIONAL): Convert tree to bit String and display.
    
    //======================\\
    // READ AND ENCODE FILE \\
    //======================\\
    char[] encodeFileChars = fileChars(ENCODE_F);
    if (encodeFileChars == null) {
      System.err.println("Warning: Could not read file to encode: " + ENCODE_F);
    }
    else {
      String encodedText = encode(bitStrings, encodeFileChars);
//      System.out.println(encodedText);
      writeFile(ENCODE_OUT_F, encodedText);
    }
    
    //======================\\
    // READ AND DECODE FILE \\
    //======================\\
    char[] decodeFileChars = fileChars(DECODE_F);
    if (decodeFileChars == null) {
      System.err.println("Warning: Coult not read file to decode: " + DECODE_F);
    }
    else {
      String decodedText = decode(root, decodeFileChars);
      
//      System.out.println("Decoded text:"); // (OPTIONAL)
  //    System.out.println(decodedText);     // (OPTIONAL)
      
      writeFile(DECODE_OUT_F, decodedText);
    }
  }
  
  
  //=========================\\
  // TREE GENERATION METHODS \\
  //=========================\\
  
  /**
   * Generates a Huffman Tree based on the frequencies of a set of chars.
   * 
   * @param chars array to base frequencies on.
   * @return      root of generated Huffman Tree.
   */
  static HuffmanNode genTree(char[] chars) {
    Map<Character,Integer> freqs = genFrequencyMap(chars);

    // TODO: Add frequency statistics/other calculations.\
    //ex how many unique characters were found
    
    // If option is set, don't just make do with the characters provided,
    //   also include certain absent characters, before converting to tree.
    if (FILL_GAPS) {
      gapCheck(freqs);
    }
    return mapToTree(freqs);
  }
  
  /**
   * Counts frequencies of each unique character from provided array.
   * 
   * @param chars set to count frequencies from.
   * @return      mappings from unique chars to their frequencies.
   */
  static Map<Character,Integer> genFrequencyMap(char[] chars) {
    Map<Character,Integer> result = new HashMap<>();
    // Could use TreeMap or HashMap. TreeMap might be nice for testing,
    //   but no long-term reason we actually need sorted chars.
    for (char c : chars) {
    	int x = c;
    	int count = 0;
    	for(int a = 0;a < chars.length; a++) {
    		int y = chars[a];
    		if(x == y ){
    			count++;
    		}
    		
    	}
    	result.put(c, count);
    }
 
      // TODO: Complete.
    
    return result;
  }
  
  /**
   * Fills in "gaps" in frequency map of characters to ensure certain
   *   characters are present, even if with a frequency of 0.
   *   
   * Gaps to check:
   * - All capital letters
   * - All lowercase letters
   * - Characters with ASCII values 32-34: ' ' '!' and '"'
   * - Characters with ASCII values 39-41: '\'' '(' and ')'
   * - Characters with ASCII values 44-47: ',' '-' '.' and '/'
   * - These characters with inconvenient ASCII values:
   *      '\n' '\r' ':' ';' '?'
   * 
   * @param freqs the frequency map to be filled in.
   */
  static void gapCheck(Map<Character,Integer> freqs) {
    // TODO (OPTIONAL): Complete.
  }
  
  /**
   * Converts a frequency map to a Huffman Tree encoding the characters from
   * the map, with the more frequent chars higher in the tree.
   *
   * @param freqs mappings from unique chars to their frequencies;
   *              must include at least one character.
   * @return      root of Huffman Tree generated.
   */
  static HuffmanNode mapToTree(Map<Character,Integer> freqs) {
    PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
    // The built-in Java PriorityQueue automatically prioritizes
    //   for *lowest* comparison first. This can use a provided Comparator,
    //   or the "natural ordering" (including Comparable objects).
    // HuffmanNodes implement Comparable<HuffmanNode> based on frequency.
    
    // Useful PriorityQueue functions: .add(), .poll(), .size()
    //   (see online documentation for details)
    
    // 1. Put all characters as leaves into the queue to start.
    //   (reminder: mapName.keySet() gives the set of all keys in
    //    a map, which can be iterated through.)
    Iterator keys  = freqs.keySet().iterator();
    while(keys.hasNext()) {
    	Map.Entry entry = (Map.Entry) keys.next();
    	
    	pq.peek().frequency = (int) entry.getKey();
    }
    // TODO: Complete.
    
    // 2. As long as there are 2+ nodes in the queue, keep merging the
    //   two lowest-frequency (highest priority) nodes from the queue,
    //   using a new parent node, and put that node into the queue
    //   (but not the children, which are now "handled").
    for(int i =  0; i < pq.size() -2; i ++) {
    	
    	int newint = pq.poll().frequency+pq.poll().frequency;
    	pq.peek().frequency = newint;
    	HuffmanNode node = pq.peek();
    	pq.add(node);
    }
    // TODO: Complete.
    
    // 3. Once there's only one node left, that's the root of the tree.
    return pq.poll(); // TODO: Complete.
  }
  
  /**
   * Generates the full standard tree from the bit String class constant.
   * 
   * @return root of standard tree.
   */
  static HuffmanNode genStdTree() {
    char[] chars = STD_TREE_BIT_STR.toCharArray();
    
    // Try this with the expectation of a possible error (Exception)
    try {
      return genStdTree(new CharArrayIterator(chars));
    }
    // If process runs out of bits to form a valid tree, it will "throw" an
    //   Exception. We can "catch" it to keep the program from crashing, and
    //   instead just give up on this tree.
    catch (NoSuchElementException nsee) {
      System.err.println("Warning: Could not read standard tree.");
      return null;
    }
    // Most types of Exception can be caught; each catch statement catches
    //   any its type could apply to. It is best practice to only catch
    //   the most specific type of Exception you are expecting and intending
    //   to handle, rather than a very general Exception type that could
    //   mask underlying problems with the code.
    
    // You could catch multiple Exceptions after a try block with additional
    //   catch blocks, and handle each differently, much like a chain of
    //   else if blocks.
  }
  
  /**
   * Recursively generates Huffman sub-tree from bit char array iterator.
   * Used for standard tree generation.
   * 
   * Postcondition: Bit char array iterator will be advanced past the 
   * bits encoding this sub-tree.
   * 
   * @param bits the iterator, advanced to the next bits
   *             to use to generate Huffman Tree nodes
   * @return     the root of this sub-tree.
   */
  static HuffmanNode genStdTree(CharArrayIterator bits) {
    // '0' represents parent node
    if (bits.next() == '0') {
      // Create parent with children based on next bits.
      return new HuffmanParent(genStdTree(bits), genStdTree(bits));
    }
    // '1' represents leaf node
    else {
      // Read in 8 bits left to right for char code.
      //   0  0  0  0    0 0 0 0
      // 128 64 32 16    8 4 2 1
      char c = 0;
      for (int bitValue = 128; bitValue > 0; bitValue /= 2) {
        // Convert each bit char into a real 0 or 1 before multiplying.
        c += bitValue * (bits.next() - '0');
      }
      
      // Create leaf with read character.
      // Frequency is irrelevant when tree is pre-constructed.
      return new HuffmanLeaf(c, 0);
    }
  }
  

  //==================================\\
  // STATIC ENCODING/DECODING METHODS \\
  //==================================\\ 
  
  /**
   * Encodes text to bit String using provided mapping (most likely generated
   * by traversing Huffman Tree, one mapping per leaf). Ignores/skips any
   * chars absent from map.
   * 
   * @param bitStrings mappings from chars to bit Strings for each character.
   * @param text       text to encode.
   * @return           the encoded version of the text
   */
  static String encode(Map<Character,String> bitStrings, char[] text) {
    StringBuilder output = new StringBuilder();
    // Strings re-build a String every time more chars are concatenated
    // onto them; StringBuilder is far more efficient (like an ArrayList).
    
    // For every character, look up bit String and add to result, if present.
    for (int i = 0; i < text.length; i++) {
      String bs = bitStrings.get(text[i]);
      if (bs != null) {
        output.append(bs);
      }
    }
    
    return output.toString();
  }
  
  /**
   * Decodes a sequence of 1s and 0s using a provided Huffman Tree.
   * 
   * @param root the Huffman Tree to use for decoding. Must be
   *             well-formed with at least 2 leaves.
   * @param bits the '1's and '0's to decode.
   * @return     decoded version of the bits, using provided tree.
   */
  static String decode(HuffmanNode root, char[] bits) {
    StringBuilder output = new StringBuilder();
    
    CharArrayIterator bitsIt = new CharArrayIterator(bits);
    while (bitsIt.hasNext()) {
      Character decoded = root.decode(bitsIt);
      
      // null result indicates no char could be generated because
      //   not enough bits were available to reach a tree leaf.
      if (decoded != null) {
        output.append(decoded);
      }
    }
    
    // Convert StringBuilder back to String.
    return output.toString();
  }

  
  //=============================\\
  // FILE INPUT / OUTPUT METHODS \\
  //=============================\\
  
  /**
   * Reads in entire file from project base directory.
   * 
   * @param fileName name of file to read.
   * @return         chars from the file, including newlines.
   *                 or null if file could not be read.
   */
  static char[] fileChars(String fileName) {
    // Scanner reads from sources like files.
    Scanner scan = null;
    
    try {
      scan = new Scanner(new File(fileName));
    }
    // If file could not be read, return nothing.
    catch (FileNotFoundException fnfe) {
      return null;
    }
    
    // Set scanner to only stop at end of file (\Z is regex code for EoF).
    scan.useDelimiter("\\Z");
    char[] chars;
    try {
      // .next() returns the String starting from current Scanner position in
      //   file including all contents up to next delimiter - in this case, the
      //   end of the file.
      chars = scan.next().toCharArray();
    }
    catch (NoSuchElementException nsee) {
      System.err.println("Warning: Empty file " + fileName + ".");
      chars = new char[0]; // 0-length array.
    }
    scan.close(); // Close file.
    
    return chars;
  }
  
  /**
   * Writes provided String to any file in project base directory.
   * 
   * @param fileName file to write to.
   * @param text     contents to put in file.
   */
  static void writeFile(String fileName, String text) {
    try {
      // PrintWriter allows writing to various destinations such as files
      // using functions similar to System.out's.
      PrintWriter out = new PrintWriter(new File(fileName));
      out.print(text);  // Write text to file.
      out.close();      // Close file.
    }
    catch (FileNotFoundException fnfe) {
      System.err.println("Warning: Could not write to file: " + fileName);
    }
  }

  
  //===========================\\
  // HUFFMAN TREE NODE CLASSES \\
  //===========================\\
  
  /**
   * Node of a Huffman Tree, which must be a parent or a leaf node. In
   * either case, nodes are comparable by frequency to enable easy
   * prioritization while building the tree.
   */
  abstract static class HuffmanNode implements Comparable<HuffmanNode> {
    /**
     * Reference to the parent of this node. Not actually needed in
     * this program, but would be needed for a bottom-up recursive
     * method to find the bit String for a given leaf/node.
     */
    HuffmanParent parent;
    
    /**
     * Frequency of the characters in or under this node. (May be 0
     * if tree is pre-generated.)
     */
    int frequency;
    
    /**
     * Compares HuffmanNodes by frequency. Required for Comparable.
     * Useful for priority queue.
     * 
     * @param other node to compare this one to
     * @return      negative if this has smaller frequency than other,
     *              positive if this has greater frequency,
     *              and 0 if they have the same frequency
     */
    public int compareTo(HuffmanNode other) {
      return frequency - other.frequency;
    }
    
    //=============================\\
    // ENCODING / DECODING METHODS \\
    //=============================\\
    
    /**
     * Generates bit map giving quick access to encoding Strings for ALL
     * chars in tree leaves, once tree is finished.
     * 
     * Kick-starts recursive setBitStrings(String, Map) method.
     * 
     * @return the mappings from each leaf character to the bit String
     *         encoding its position in the tree.
     */
    Map<Character, String> bitStrings() {
      Map<Character, String> bitMap = new HashMap<>();
      // It takes no bits to get to the root node: ""
      setBitStrings("", bitMap);
      return bitMap;
    }

    /**
     * Recursively traverses tree from top to bottom, building up bit Strings
     * and adding them to map when leaves are reached.
     * 
     * Helper function for bitStrings().
     * 
     * @param prefix Bit String representing pathway from the root down
     *               to this node. Any children will add to this.
     * @param bitMap Map of bit Strings to add to when leaves are reached.
     */ 
    abstract void setBitStrings(String prefix, Map<Character, String> bitMap);
    
    /**
     * Decodes a bit sequence by following corresponding tree branches
     * from this root node. Will only read until a leaf is found; will not
     * attempt to use all bits.
     * 
     * @param bits iteration to advance through while decoding, advanced to
     *             appropriate state for next character
     * @return     decoded Character OR null (if leaf node not reached)
     */
    abstract Character decode(CharArrayIterator bits);
    
    //=============================\\
    // TREE REPRESENTATION METHODS \\
    //=============================\\
    
    /**
     * Builds bit String representation of tree starting from this root.
     * Each node is represented in pre-order by:
     *    0 for parent node (followed by its children), OR
     *    1 for child node, followed by 8-bit ASCII code for its character
     * 
     * Kick-starts recursive buildBitRep(StringBuilder) method.
     * 
     * @return bit String representing this tree. 
     */
    String bitRep() {
      StringBuilder sb = new StringBuilder();
      buildBitRep(sb);
      return sb.toString();
    }
    
    /**
     * Recursively builds bit String representation of sub-tree rooted at this
     * node, working its way down tree in pre-order.
     * 
     * Helper function for bitRep().
     * 
     * @param sb bit representation to append to (encoding nodes down to this
     *           node in tree)
     */
    abstract void buildBitRep(StringBuilder sb);
    
    /**
     * Displays tree with root at left, tree "descending" to right. Display of
     * 0s & 1s in tree can be toggled with DISP_ALL_BITS constant.
     * 
     * Kick-starts recursive display(StringBuilder) method.
     */
    void display() {
      display(new StringBuilder());
    }
    
    /**
     * Displays this section of tree, using prefix to indent tree appropriately.
     * 
     * Helper function for display().
     * 
     * @param prefix contents to put before any children. May be whitespace or
     *               0s and 1s, but it should match the spacing already printed
     *               before this function call, so that both children are
     *               displayed with the same indentation.
     */
    abstract void display(StringBuilder prefix);
  }
  
  /**
   * Parent node in Huffman tree. Node that valid Huffman Tree nodes always
   * have two children (parents) or none (leaves). Children are labeled
   * as zero and one although this is easily represented as left and right.
   */
  static class HuffmanParent extends HuffmanNode {
    HuffmanNode zeroChild;
    HuffmanNode oneChild;

    //=================================\\
    // CONSTRUCTOR & METHODS TO FINISH \\
    //=================================\\
    
    /**
     * Sole constructor. Constructs parent node for two children,
     * calculates the resulting total frequency, and sets childrens'
     * parent references.
     * 
     * @param zero the "left" child node. Must be non-null.
     * @param one  the "right" child node. Must be non-null.
     */
    HuffmanParent(HuffmanNode zero, HuffmanNode one) {
      zeroChild = zero;
      oneChild = one;
      
      zeroChild.parent = this;
      oneChild.parent = this;
      
      zeroChild.parent.frequency =  zero.frequency;
      oneChild.parent.frequency = one.frequency;
      
      // TODO: Set parent node frequency.
    }
    
    // Overridden functions can inherit Javadoc comments from superclass.
    // This one was abstract, so is required to be implemented here.
    void setBitStrings(String prefix, Map<Character, String> bitMap) {
      // Recursively set bit Strings of both children, using correct prefix.
    	bitMap.keySet();
    	
      // TODO: Complete, so encoding has an accurate map to work with.
    }
    
    // Abstract override.
    Character decode(CharArrayIterator bits) {
      // If bits run out partway down the tree, return null
      // instead of real char.
      if (!bits.hasNext()) {
        System.err.println("Warning: Ran out of bits during decode.");
        return null;
      }
      
      // Recursively decode by following the correct branch, continuing
      // to get bits from iterator.

      // TODO: Complete, so decoding will return actual characters.      
      bits.next();//returns a 0 or 1 
      // This function call is provided for you for two reasons:
      // 1. Demonstrates how to get next character from iterator.
      // 2. Prevents infinite loop due to iterator that never advances.

      return null;
    }
    
    // Abstract override.
    void buildBitRep(StringBuilder sb) {
      // Tree parent nodes are encoded in pre-order as 0s, followed by
      // the codes for each of their children, which may themselves be
      // sub-trees or leaves.
      
      // TODO (OPTIONAL): Complete to add the sub-tree rooted at this 
      //   node to the StringBuilder.
    }
    
    //==================\\
    // COMPLETED METHOD \\
    //==================\\
    
    // Abstract override.
    void display(StringBuilder prefix) {
      // For each child, set the prefix indentation correctly, display the
      // child, then "back up" the indentation so the next nodes can display.
      
      // 0 child
      System.out.print("0 ");
      // 0 has been printed on one line for this child, but further
      // descendants on different lines may or may not want the bits
      // to be displayed beforehand.
      if (DISP_ALL_BITS) {
        prefix.append("0 ");
      }
      else {
        prefix.append("  ");
      }
      // Recursively display 0 child.
      zeroChild.display(prefix);
      // Back up the indentation for the 0 child now that it's done.
      prefix.setLength(prefix.length()-2);

      // Whether it's bits or whitespace, indent the 1 child so it
      // is aligned with the 0 child.
      System.out.print(prefix);
      
      // 1 child
      System.out.print("1 ");
      if (DISP_ALL_BITS) {
        prefix.append("1 ");
      }
      else {
        prefix.append("  ");
      }
      oneChild.display(prefix);
      prefix.setLength(prefix.length()-2);
    }
  }
  
  /**
   * Leaf node of Huffman Tree.
   */
  static class HuffmanLeaf extends HuffmanNode {
    /**
     * Character represented by this node.
     */
    Character c;
    
    /**
     * Sole constructor. Constructs leaf for given character at
     * specified frequency.
     * 
     * @param c         represented character.
     * @param frequency character's frequency in source. May be 0 if tree
     *                  is pre-determined rather than built from source.
     */
    HuffmanLeaf(Character c, int frequency) {
      this.c = c;
      this.frequency = frequency;
    }
    
    //===================\\
    // METHODS TO FINISH \\
    //===================\\
    
    // Abstract override.
    void setBitStrings(String bitCode, Map<Character, String> bitMap) {
      // TODO: Complete so leaf character's code is placed into map.
    }
    
    // Abstract override.
    Character decode(CharArrayIterator bits) {
      // TODO: Complete so actual character result is returned based on bits.
      return null;
    }
    
    //===================\\
    // COMPLETED METHODS \\
    //===================\\
    
    // Abstract override.
    void buildBitRep(StringBuilder sb) {
      // Appends a leaf to the bit String as follows:
      
      // Each leaf node is represented by 1 followed by 8-bit char code.
      sb.append('1');
      
      // This is complex. Don't panic!
      
      // 0x100 (hexadecimal) = 1 0000 0000 (binary)
      // | is "bitwise or" - each aligned bit of the operands are "or'd"
      //   1 | 1 = 1         1 | 0 = 1
      //   0 | 1 = 1         0 | 0 = 0
      
      // ex: 0011          1 0000 0000      
      //   | 1010        | 0 0110 1010
      //   = 1011        = 1 0110 1010
      
      // So, 0x100 & c means:
      // - The rightmost 8 bits of the character code for c control those bits
      //   of the result.
      // - The 9th (leftmost) bit is always 1.
      
      // So, the String conversion function works, but won't remove leading
      //   0s in the 8 right bits as it normally does (due to 9th bit being 1).
      
      // Then, .substring(1) gets the substring that skips the leftmost bit
      //   (the always-1 bit) leaving exactly the 8 bits of the char code,
      //   including leading 0s.
      sb.append(Integer.toBinaryString(0x100 | c).substring(1));
    }
    
    // Abstract override.
    void display(StringBuilder prefix) {
      // Prefix parameter is needed for branches only. All parent nodes
      // handle the prefix for their children; this node only needs
      // to display its own info.
      
      // Display newline without disrupting tree structure.
      if (c == '\n') {
        System.out.println("'\\n'");
      }
      // Same for carriage return.
      else if (c == '\r') {
        System.out.println("'\\r'");
      }
      // Display non-line-breaking chars directly, in single quotes.
      else {
        System.out.println("'" + c + "'");
      }
    }
  }
  
  
  //=====================\\
  // CHAR ITERATOR CLASS \\
  //=====================\\
  
  /**
   * Iterator over a char[]. Standard iterators don't work on primitive
   *   types, but we can build our own.
   */
  static class CharArrayIterator {
    /**
     * Index of next element to iterate through.
     */
    private int i = 0;
    /**
     * Array to iterate through.
     */
    private char[] a;
    
    /**
     * Sole constructor. Constructs a new iterator over the specified array.
     * @param chars the char array to iterate over
     */
    CharArrayIterator(char[] chars) {
      a = chars;
    }

    /**
     * Returns the next available char.
     * @return true if the iteration has more elements
     */
    boolean hasNext() {
      return i < a.length;
    }
    
    /**
     * Returns the next available char.
     * 
     * @return the next element in the iteration
     * @throws NoSuchElementException - if iteration has no more elements
     */
    char next() {
      // Crash with correct Exception when out of elements.
      if (i >= a.length) {
        throw new NoSuchElementException("Only " + a.length + "elements.");
        // Exceptions can contain messages.
        
        // You can throw any class that extends Throwable, including
        // your own. Try to extend the most applicable existing
        // Exception class if making your own.
        
        // Class hierarchy in this case:
        // NoSuchElementException extends
        //       RuntimeException extends
        //              Exception extends
        //              Throwable extends Object
      }
      
      return a[i++];
      // Equivalent to:
      // char c = a[i];
      // i++;
      // return c;
    }
  }
}