
import processing.core.*; //brings in processing core classes
import java.util.*;
//Graphs: group of interconnected nodes. 
//Useful for representing different types of information
//ex: cities w/ connections between them(roads)
//people with friendships between each other
//vertexes/vertcies: nodes
//edges: connections to "Neighboring" nodes/vertexes
	// today's topic: Graphs (Graph Theory)
	// Graphs: group of interconnected nodes
	//  examples: cities w/connections b/w them (roads)
//	    people w/friendships
	// vertexes/vertices: nodes
	// edges: connections to "neighboring" nodes/vertexes

	public class CS02b_Lesson12_Graphs extends PApplet {
	  ArrayList<Node> nodes = new ArrayList<>();
	  Node selected = null;

	  int[][] edges = new int[1000][1000];
	  // instead of having an object for each vertex, we could simply
	  //   store edges in a grid
	  // row index: index of a node with an outgoing connection
	  // col index: index of node with the incoming connection
	  // value: strength (or presence) of connection from row node to col node

	  // weighted graphs: nodes are connected with a weight/strength instead of T/F
	  // directed graphs: edges have a direction, may be one way or two way

	  public void setup() {
	    size(800, 600);
	    //ellipse(20, 40, 50, 60);
	  }

	  // overriding setup/draw/etc funcs, and in PApplet those are public
	  //   we're not allowed to "reduce visibility" from public to non-public
	  public void draw() {
	    background(0);  // black
	    for (Node n : nodes) {
	      // no index access to AL when using for-each loop, but still in order
	      n.display();
	    }

	    if (selected != null) {
	      stroke(255, 0, 0);  // red
	      strokeWeight(3);
	      selected.display();
	      stroke(0);         // black
	      strokeWeight(1);  
	    }
	  }






	  public void keyPressed() {
	    if (key == 'n') nodes.add(new Node(nodes.size()));
	    // ex: 0, 1, 2   (length is 3, next new node will have id=3)
	  }

	  public void mousePressed() {
	    if (mouseButton == LEFT) {
	      selected = findNode(mouseX, mouseY);
	    }
	    if (mouseButton == RIGHT && selected != null) {
	      // right click dis/connects selected and clicked nodes
	      Node clicked = findNode(mouseX, mouseY);

	      if (clicked != null) {
//	        if (clicked.neighbors.contains(selected)) {
	        if (edges[clicked.id][selected.id] != 0) {
	          // these are already connected: remove edges
	          clicked.neighbors.remove(selected);  // 
	          selected.neighbors.remove(clicked);

	          edges[clicked.id][selected.id] = 0;
	          edges[selected.id][clicked.id] = 0;
	        }
	        else {
	          // were not previously connected; connect them
	          clicked.neighbors.add(selected);
	          selected.neighbors.add(clicked);

	          edges[clicked.id][selected.id] = 1;  // equivalent statements using
	          edges[selected.id][clicked.id] = 1;  //    grid
	        }
	      }
	    }
	  }

	  Node findNode(float px, float py) {
	    Node result = null;
	    for (Node n : nodes) {
	      if (n.inside(px, py)) result = n;
	    }
	    return result;
	  }

	  class Node {
	    float x, y;  // data
	    // what data structure could store other Nodes we have connections to
	    Set<Node> neighbors = new HashSet<>();

	    int id;  // index within Nodes array
	    
	    Node(int id) {
	      x = random(0, width);
	      y = random(0, height);
	      // random, width, and height are inherited from PApplet

	      this.id = id;
	    }

	    void display() {
	      ellipse(x, y, 30, 30);
	      stroke (255);   // white
	      // for-each loop: n will be set equal to each neighbor one by one
	      for (Node n : neighbors) {
	        line(x, y, n.x, n.y);
	      }
	      stroke(0);  // black
	    }

	    boolean inside(float px, float py) {  return dist(px,py,x,y) <= 15; }
	  }
	  
	}

	// homework
	// 4. connections b/w graph nodes
	// 3 nodes: 3*2 / 2  (each of 3 pairs with 2 others, but we double counted)
//	     3
	// 4 nodes: 4*3 / 2  (each of 4 nodes pair with 3 others, but don't double)
//	     6
	// n nodes: n*(n-1) / 2
















