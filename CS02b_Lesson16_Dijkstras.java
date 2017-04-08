// Welcome back to CS02b!

import processing.core.*;
import java.util.*;

public class CS02b_Lesson16_Dijkstras extends PApplet {
  // main method inherited from PApplet (Processing applet)

  ArrayList<Node> nodes = new ArrayList<Node>();

  Node selected = null;
  List<Node> path = null;

  public void setup() {
    size(800, 600);
  }

  public void draw() {
    background(0);
    stroke(0); // black
    strokeWeight(1);
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
      if (hover != null) {
        List<Node> path = randomPath(selected);
      }
    }
    dispPath(path);
  }

  
  
  
  
  // returns path from origin to dest, or null if none exists
  List<Node> dijkstra(Node origin, Node dest) {
	  PriorityQueue<DStep> pq = new PriorityQueue<>();
	  Map<Node,DStep> bestSteps = new HashMap<>();
	// e.g. trying to get from A to Z
	   // map:
	   //   A => (null->A, 0)
	   //   B => (A->B, 10)
	   //   C => (A->C, 20)
	   //   M => (C->M, 22)
	   //   Z => (M->Z, 44)   (even if other paths led to Z)
	   // full path we want from A->Z with good dist:
	   //   A->C->M->Z
	   // looping backwards from dest tells us which earlier nodes
	   //   we wanted, one step at a time
	    // first "step" is from "nowhere" to the origin, dist of 0

	  	DStep startStep = new DStep(null, origin, 0);
	    pq.add(startStep);
	    bestSteps.put(origin,startStep);
	   //loop until either p1 is empty or we find dest
	   // pull next best step from pq
       // make sure you haven't already found a better path to that step's end node
	    
		while(!pq.isEmpty()) {
	   		DStep nextStep = pq.poll();
	        if (bestSteps.containsKey(nextStep.end)) continue;
	        Node n = nextStep.end;
	        if (dest == n) break;
	        for(Node neighbor : n.neighbors.keySet()) {
	            pq.add(new DStep(n, neighbor, nextStep.totalDist + n.neighbors.get(neighbor)));
	        }

	   		
	   	}
	  if (!bestSteps.containsKey(dest)) return null;

	   List<Node> bestPath = new ArrayList<>();
	// TODO
	    // reconstruct the best path to dest based on the "logged" steps for
	    //   for the relevant nodes (loop in reverse)
	   
		   
	   
		   return bestPath;
  }
  //represents one step of Dijkstra's algorithm, including the start node(for that one step), the dest node(for that one step)
  //and total distance from the absolute start node to the dest node by traveling through
//ex: (B->C, 120)  means that you can get to C through B with a total
 //   distance of 120    (B probably has a separate way to get to itself
 //                       from some other node with some other distance)

  class DStep implements Comparable<DStep> {
	  Node middle, end;
	  int totalDist;
	  
	  DStep(Node a, Node b, int c) {
		  middle = a;
	      end = b;
	      totalDist = c;

	  }
	  public String toString() {
		  return "[" + middle + "->" + end + "," + totalDist + "]";

	  }
	  
	  public int compareTo(DStep other) {
		  return this.totalDist-other.totalDist;
	  }
	  
	  
  }
  

  void dispPath(List<Node> path) {
    if (path == null || path.isEmpty()) return;

    stroke(255, 0, 0);
    strokeWeight(5);
    Node lastNode = null;
    for (Node n : path) {
      if (lastNode != null) {
        line(lastNode.x, lastNode.y, n.x, n.y);
      }
      lastNode = n;
    }
  }

  List<Node> randomPath(Node start) {
    List<Node> result = new ArrayList<>();
    Node curNode = start;

    for (int i = 0; i < 4; i++) {
      if (curNode == null) return result;

      result.add(curNode);
      curNode = curNode.randomNeighbor();
    }
    
    return result;
  }

  public void keyPressed() {
    if (key == 'n') {
      nodes.add(new Node(nodes.size()));
    }
  }

  public void mousePressed() {
    if (mouseButton == LEFT) {
      selected = findNode(mouseX, mouseY);
      path = randomPath(selected);
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

        if (Math.abs(n.x-x) > Math.abs(n.y-y)) {
          text(neighbors.get(n), midX, midY + 24);
        }
        else {
          text(neighbors.get(n), midX + 24, midY);
        }
      }
      stroke(0);
    }

    boolean inside(float px, float py) {
      // returns true if (px, py) is inside this Node
      return dist(px, py, x, y) <= 15;
    }

    // please write random neighbor function
    Node randomNeighbor() {
      if (neighbors == null || neighbors.isEmpty()) return null;

      int choice = (int) random(0, neighbors.size());
      choice = (int) (Math.random() * neighbors.size());

      int count = 0;
      for (Node n : neighbors.keySet()) {
        if (count == choice) return n;
        count++;
      }
      return null;
    }
    public String toString() {
        return "(" + x + "," + y + ")";
      }

    // you'll have to pick a random number, and count through the for-each
  }
}





