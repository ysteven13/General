/*to remove edges from a node edges can go in one direction instead of both ways one direction is called a directed graph
- to get nodes two degrees or fewer use a limited bfs search and search outwards and use a counter to determine if the node is two or more
- using boolean matrix use t/f to represent edges nest two loops together to check two levels down in larger cases use traditional search and a counter

- to get all connected components it does not matter what type of search you use. You want the full set of nodes
- using a fringe can keep track of what nodes you reach but not explore
- to keep track of the things in the dfs
-do not use recursive, make two data strucutre to keep track of the nodes we checked and the nodes that are remaining
- then you can use those two structures to keep track then do a standard dfs

-for checking nodes with most neighbors loop through the nodes then check the size and return;
*/
import processing.core.*;  // brings in Processing core classes
	import java.util.*;

public class CS02b_lesson15 extends PApplet{
	  // main method inherited from PApplet (Processing applet)

	  ArrayList<Node> nodes = new ArrayList<Node>();

	  Node selected = null;
	  List<Node> path = null;
	  public void setup() {
	    size(800, 600);
	  }

	  public void draw() {
	    background(0);
	    for (Node n : nodes) {
	      n.display();
	    }
	    if (selected != null) {
	      stroke(255, 0, 0); // red
	      strokeWeight(3);
	      selected.display();
	      stroke(0); // black
	      strokeWeight(1);
	      
	      Node hover = findNode(mouseX, mouseY);
	      if(hover!= null) {
	    	  List<Node> path = dijkstra(selected, hover);
	    	  dispPath(path);
	      }
	    }
	  }
	  // returns path from origin to dest, or null if none exists

	  List<Node> dijkstra(Node origin, Node dest) {
		  return null;
	  }
	  void dispPath(List<Node> path)  {
		  if(path == null || path.isEmpty()) return;
		  
		  Node lastNode = null;
		  stroke(255, 0, 0);
		  strokeWeight(5);
		  for(Node n : path) {
			  if(lastNode != null) {
				  line(lastNode.x,lastNode.y, n.x,n.y);
			  }
			  lastNode = n;
		  }
	  }
	  List<Node> randomPath(Node start) {
		  List<Node> result = new ArrayList<>();
		  Node curNode = start;
		  	for(int i = 0; i < 4; i++) {
		  		if(curNode==null)return result;
		  		result.add(curNode);
		  		curNode = curNode.randomNeighbor();
		  	}
		  
		  
		  return result;
	  }

	  public void keyPressed() {
	    if (key == 'n') {
	      nodes.add(new Node(nodes.size()));
	      path = randomPath(selected);
	    }
	    
	  }
	  public void mousePressed() {
	    if (mouseButton == LEFT) {
	      selected = findNode(mouseX, mouseY);
	    }
	    else {
	      // right click creates connections b/w nodes
	      if (selected != null) {
	        Node other = findNode(mouseX, mouseY);
	        if (other != null) {
	          // add another if statement inside this one that checks to see
	          //   if these are already connected using adjacent LISTs, and
	          //   if they are, then disconnect them instead of connecting
	          if (other.neighbors.containsKey(selected)) {
	            selected.neighbors.remove(other);
	            other.neighbors.remove(selected);
	          }
	          else {
	            int weight = (int) dist(other.x, other.y, selected.x, selected.y);
	            selected.neighbors.put(other, weight);
	            other.neighbors.put(selected, weight);
	          }
	        }
	      }
	    }
	  }

	  public void mouseDragged() {
	    if (selected != null && mouseButton == LEFT) {
	      selected.x = mouseX;
	      selected.y = mouseY;
	    }
	  }

	  // finds the top node that contains px/py (or null)
	  Node findNode(float px, float py) {
	    Node result = null;
	    for (Node n : nodes) {
	      if (n.inside(px, py)) result = n;
	    }
	    return result;
	  }

	  // graph: collection of interconnected nodes
	  // edge: connection between two nodes
	  // vertex: synonym for node
	  class Node {
	    // adjacency list
	    Map<Node, Integer> neighbors = new HashMap<>();
	    float x, y;
	    int id;  // index where Node is stored

	    Node(int id) {
	      x = random(0, width);
	      y = random(0, height);
	      // random, width, height inherited from PApplet
	      this.id = id;
	    }

	    void display() {
	      ellipse(x, y, 30, 30);

	      stroke(255);
	      for (Node n : neighbors.keySet()) {
	        line(x, y, n.x, n.y);
	        textSize(18);
	        textAlign(CENTER, CENTER);
	        float midX = (x + n.x) / 2;
	        float midY = (y + n.y) / 2;
	        
	        if(Math.abs(n.x-x) > Math.abs(n.y-y)) {
	        	 text(neighbors.get(n), midX, midY + 24);
	        }else {
	        	text(neighbors.get(n), midX + 25, midY + 24);
	        }	      }
	      stroke(0);
	    }

	    boolean inside(float px, float py) {
	      // returns true if (px, py) is inside this Node
	      return dist(px, py, x, y) <= 15;
	    }
	    //write the random neighbor function
	    Node randomNeighbor() {
	    	if (neighbors == null || neighbors.isEmpty()) return null;

	        int choice = (int) random(0, neighbors.size());
	        int count = 0;
	        for(Node n : neighbors.keySet()) {
	        	
	        	if(count == choice) {
	        		return n;
	        	}
	        	count++;
	        }
	        return null;
	    }
	    
	    
	  }
	}



