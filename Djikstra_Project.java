import java.util.*;
import processing.core.*;


public class Djikstra_Project extends PApplet{
//Project 3: Pathfinder
//
//Your third and final project involves finding paths around
//a series of wall obstacles, using the graph search algorithms
//you've learned.
//
//This starter code sets up the framework of the program, including
//the walls themselves, and a player and "ghosts" (think Pac-man)
//that move around. The starter code has two modes, "build" and "play".
//Build allows you to design levels out of walls. Two are already
//built in. If you make a level you like, you can even generate
//array code for it that you can then copy into this source code to
//access it later.



//For the second week, complete the project:
//1. Make the ghosts and player constantly update their connections
// to the other points of the graph (and possibly each other)
// so that paths can go to or from themselves.
//2. Add movement modes for the ghosts so one each follows:
//   a. A dfs-based path to the player.
//   b. A bfs-based path to the player.
//   c. A dijkstra-based path to the player.
//   d. Random pathways - preferably never going back to the same
//      point right away!
//3. Optionally, add other game-like features to this project.
//   a. An end screen / reset when a ghost reaches a player.
//   b. A mode where the player chases the ghosts.
//      (How could you determine which nodes are best to get as
//       far from the player as possible using graph search?)
//   c. Anything else you like!






// used for Serializable interface inherited via PApplet
private static final long serialVersionUID = 1L;

// predefined sets of walls
// major index:  which set of walls
// middle index: which wall
// minor index:  which coordinate (x1, y1, x2, y2)
float[][][] preWalls = new float[][][]{
 // WALL SET 0
 {
   {356.0f,701.0f,278.0f,720.0f},
   {156.0f,292.0f,311.0f,452.0f},
   {344.0f,625.0f,268.0f,644.0f},
   {478.0f,586.0f,429.0f,533.0f},
   {429.0f,185.0f,463.0f,539.0f},
   {644.0f,393.0f,627.0f,473.0f},
   {616.0f,411.0f,530.0f,500.0f},
   {764.0f,525.0f,619.0f,461.0f},
   {158.0f,308.0f,319.0f,196.0f},
   {756.0f,532.0f,772.0f,443.0f},
   {497.0f,676.0f,470.0f,571.0f},
   {339.0f,672.0f,388.0f,550.0f},
   {525.0f,190.0f,348.0f,199.0f},
   {678.0f,565.0f,566.0f,453.0f},
   {252.0f,568.0f,339.0f,547.0f},
   {471.0f,585.0f,484.0f,521.0f},
   {287.0f,722.0f,260.0f,565.0f},
   {367.0f,713.0f,391.0f,641.0f},
   {687.0f,499.0f,712.0f,435.0f},
   {161.0f,471.0f,176.0f,169.0f},
   {384.0f,568.0f,209.0f,412.0f},
   {397.0f,659.0f,320.0f,580.0f},
 },
 // WALL SET 1
 {
   {801.0f,378.0f,756.0f,355.0f},
   {779.0f,536.0f,707.0f,528.0f},
   {852.0f,417.0f,936.0f,333.0f},
   {280.0f,479.0f,274.0f,146.0f},
   {948.0f,541.0f,852.0f,404.0f},
   {859.0f,555.0f,798.0f,260.0f},
   {734.0f,275.0f,612.0f,199.0f},
   {564.0f,514.0f,596.0f,194.0f},
   {383.0f,349.0f,312.0f,351.0f},
   {635.0f,571.0f,345.0f,532.0f},
   {572.0f,113.0f,547.0f,448.0f},
   {455.0f,190.0f,382.0f,198.0f},
   {485.0f,564.0f,477.0f,193.0f},
   {906.0f,261.0f,792.0f,264.0f},
   {404.0f,155.0f,150.0f,167.0f},
   {464.0f,344.0f,411.0f,352.0f},
   {932.0f,345.0f,911.0f,273.0f},
   {151.0f,218.0f,35.0f,163.0f},
   {553.0f,446.0f,494.0f,216.0f},
   {425.0f,495.0f,405.0f,207.0f},
   {53.0f,414.0f,42.0f,154.0f},
   {173.0f,192.0f,269.0f,521.0f},
   {550.0f,184.0f,421.0f,215.0f},
   {839.0f,222.0f,761.0f,202.0f},
   {313.0f,511.0f,321.0f,201.0f},
   {702.0f,409.0f,730.0f,246.0f},
   {405.0f,507.0f,390.0f,193.0f},
   {502.0f,211.0f,502.0f,437.0f},
   {716.0f,420.0f,554.0f,509.0f},
   {714.0f,533.0f,770.0f,196.0f},
   {140.0f,227.0f,64.0f,296.0f},
   {195.0f,188.0f,78.0f,525.0f},
   {205.0f,358.0f,137.0f,397.0f},
 },
 {
     {501.0f,426.0f,531.0f,244.0f},
     {564.0f,569.0f,296.0f,465.0f},
     {402.0f,343.0f,327.0f,186.0f},
   },
};

Set<Point> wallPoints = new HashSet<>();
Set<Wall> allWalls = new HashSet<>();

// backups used when loading or deleting walls
Set<Point> allPointsBackup = null;
Set<Wall> allWallsBackup = null;

// shared variables
Point mouse = new Point();

// mode variables
final int BUILD_MODE = 0;
final int PLAY_MODE = 1;
final int CONNECTION_MODE = 2;
final int MODE_COUNT = 3; // update this when adding new modes!

int mode = BUILD_MODE;

// build variables
Point lastPoint = null;  // last point created

// play variables
boolean playPaused = false;

Player player;
Mover[] ghosts;

public void setup() {
 size(1000, 800);
 colorMode(HSB, 360, 100, 100, 100);
 loadWalls(0);
}

public void draw() {
 // keep mouse Point updated
 mouse.x = mouseX;
 mouse.y = mouseY;
 
 if (mode == BUILD_MODE) {
   drawBuild();
 } else if(mode == CONNECTION_MODE) {
	 drawConnections();
 }
 else if (mode == PLAY_MODE) {
	drawConnections();
  // playGame();
 }
 else {
   background(0); // black
   fill(0, 10, 100); // 10% red
   textAlign(CENTER, CENTER);
   textSize(48);
   text("Invalid mode!", width/2, height/2);
 }
}

float dist(Point a, Point b) {
 return dist(a.x, a.y, b.x, b.y);
}

// draws line from p to p2, with intersections with walls
void drawCrossingLine(Point p, Point p2) {
 Set<Point> intersects = new HashSet<>();
 for (Wall w : allWalls) {
   Point wallIntersect = intersect(p, p2, w.p1, w.p2);
   
   // add any intersect that isn't basically at ends
   if (wallIntersect != null && dist(p, wallIntersect) > 0.1 &&
                                dist(p2, wallIntersect) > 0.1) {
     intersects.add(wallIntersect);
   }
 }

 strokeWeight(3);
 if (intersects.isEmpty()) {
   stroke(120, 100, 50); // green
   line(p.x, p.y, p2.x, p2.y);
 }
 else {
   stroke(0, 100, 100); // red
   line(p.x, p.y, p2.x, p2.y);
   
   for (Point intersect : intersects) intersect.display();
 }
}

// does some algebra/geometry to find intersection of line a1-a2 with b1-b2
//   or null if there is none
Point intersect(Point a1, Point a2, Point b1, Point b2) {
 // if line b is vertical, swap it with line a, so "a is vertical"
 //   code will apply just as easily to the opposite case
 if (b1.x == b2.x) {
   Point c1 = a1;
   Point c2 = a2;
   a1 = b1;
   a2 = b2;
   b1 = c1;
   b2 = c2;
 }
 // line a is vertical
 if (a1.x == a2.x) {
   // both are vertical
   if (b1.x == b2.x) return null;
   
   // line b is left or right of line a
   if (Math.min(b1.x, b2.x) < a1.x ||
       Math.max(b1.x, b2.x) > a1.x) return null;
   
   // calculate intercept
   float yIntercept = b1.y + (b1.y-b2.y)/(b1.x-b2.x)*(a1.x - b1.x);
   
   // line b is above or below line a
   if (yIntercept < Math.min(a1.y, a2.y) ||
       yIntercept > Math.max(a1.y, a2.y)) return null;
   
   return new Point(a1.x, yIntercept);
 }
 
 // we now know neither line is vertical
 // we wish to find a point where ya = yb, xa = xb
 //   point-slope form -> slope-intercept
 //     y - y1 = m(x-x1)
 //     y = mx + (y1 - mx1)
 //   set y's equal to solve the system of equations
 //     ma*x + (y1a - ma*x1a) = mb*x + (y1b - mb*x1b)
 //     x*(ma - mb) = y1b - mb*x1b - y1a + ma*x1a
 //     x = (y1b - mb*x1b - y1a + ma*x1a) / (ma - mb)
 
 float aSlope = (a1.y - a2.y) / (a1.x - a2.x);
 float bSlope = (b1.y - b2.y) / (b1.x - b2.x);
 
 // parallel lines
 if (aSlope == bSlope) return null;
 
 float xIntercept = (b1.y - bSlope*b1.x - a1.y + aSlope*a1.x)
                  / (aSlope - bSlope);
 
 // check if x is outside of bounds of either line
 if (xIntercept < Math.min(a1.x, a2.x) ||
     xIntercept > Math.max(a1.x, a2.x) ||
     xIntercept < Math.min(b1.x, b2.x) ||
     xIntercept > Math.max(b1.x, b2.x)) return null;
 
 float yIntercept = a1.y + (xIntercept - a1.x) * aSlope;
 
 return new Point(xIntercept, yIntercept);
}

void drawBuild() {
 background(120, 50, 100);  // green
 for (Wall w : allWalls) w.display();
 
 // draw last point and line connecting it to mouse
 if (lastPoint != null) {
   drawCrossingLine(lastPoint, mouse);
   lastPoint.display();
 }
 
 // display instructions
 textAlign(LEFT, BOTTOM);
 int tSize = 12;
 textSize(tSize);
 fill(0, 40);  // black, 40% opacity
 int y = 5;
 
 text("Build mode.", 5, y += tSize);
 text("space: switch mode (works in both modes)", 5, y += tSize);
 text("w: Add random wall.", 5, y += tSize);
 text("g: Print code to generate current walls.", 5, y += tSize);
 text("#: load pre-defined wall set (0-" + (preWalls.length-1) + ")",
      5, y += tSize);
 text("d: delete all", 5, y+= tSize);
 text("r: revert to before last load/delete", 5, y += tSize);
 text("click: create endpoint", 5, y += tSize);
 text("right-click endpoint: remove wall", 5, y += tSize);
 text("click and drag: move endpoint", 5, y += tSize);
 text("", 5, y += tSize);
}

void drawConnections() {  
//TODO
	for(Point p: wallPoints) {
		for(Point p2: p.neighbors.keySet()) {
			stroke(150);
			line(p.x,p.y,p2.x,p2.y);
		}
	//return;
	}
}

void playGame() {
 background(0, 0, 100);  // white
 

 player.move();
 if (frameCount % 2 != 0) player.display();
 for (Mover m : ghosts) {
   m.move();
   m.display();
 }
 if (frameCount % 2 == 0) player.display();
 
 for (Wall w : allWalls) w.display();
}

public void keyPressed() {
 if (mode == BUILD_MODE) {
   if (key == ' ') {
     // change mode
     cleanupBuild();
     initPlay();
   }
   // new Walls add themselves to Set automatically!
   if (key == 'w') new Wall();
   if ('0' <= key && key <= '9') loadWalls(key - '0');
   if (key == 'r') revertWalls();
   if (key == 'd') clearWalls();
   if (key == 'g') {
     System.out.println("WALLS ARRAY (copy into code):");
     System.out.println(wallsString());
   }
 }
 else if (mode == PLAY_MODE) {
   if (key == ' ') {
     cleanupPlay();
     initBuild();
   }
 }
}

void loadWalls(int index) {
 if (index >= preWalls.length) return;
 
 clearWalls();
 
 // preWalls[index]: selected array of walls
 // wall:            one wall from array, in float[] form
 for (float[] wall : preWalls[index]) {
   new Wall(wall[0], wall[1], wall[2], wall[3]);
 }
}

void clearWalls() {
 lastPoint = null;
 
 allWallsBackup = allWalls;
 allPointsBackup = wallPoints;
 
 allWalls = new HashSet<>();
 wallPoints = new HashSet<>();
}

// swaps walls with backups, effectively reverting (or re-doing)
//   wall load/delete
void revertWalls() {
 lastPoint = null;
 
 Set<Wall> swapperW = allWalls;
 allWalls = allWallsBackup;
 allWallsBackup = swapperW;
 
 Set<Point> swapperP = wallPoints;
 wallPoints = allPointsBackup;
 allPointsBackup = swapperP;
}

// generates String intended to be pasted into Wall[] preWalls
//   in top of source code, to provide a new pre-defined set of
//   walls, based on current walls
String wallsString() {
 StringBuilder sb = new StringBuilder();
 sb.append("    {");
 for (Wall w : allWalls) {
   sb.append("\n      ");
   sb.append(w.arrayString());
   sb.append(",");
 }
 sb.append("\n    },");
 
 return sb.toString();
}

void initBuild() {
 mode = BUILD_MODE;
}

//For the first week, aim to:
//1. Familiarize yourself with the code.
//2. When switching out of Build Mode, create a graph representing
//movement options among the walls.
//The walls already have Points at their ends, but they are not
//configured to have any connections other than the walls themselves.
//One can move from Point a to Point b if:
// a. Point a is not part of the same wall as Point b.
// b. There are no intersections with walls between a and b.
//3. Add an additional game mode that displays graph connections.
//This will also help you debug part 2.
//everytime we go through play mode we should reupdate the graph in reference to the moving things.

void dfs(Point target,Point start, int curDepth, int depthLimit ) {
	Deque<Point> deque = new LinkedList<Point>();
	Map<Point, Integer> depths = new HashMap<Point, Integer>();
	deque.push(start);
	depths.put(start, 0);
	List<Point> ret = new LinkedList<Point>();
	while(!deque.isEmpty()) {
		Point point = deque.pop();
		
	}
	
}
void bfs() {
	
}
void dijkstra() {
	
}






void cleanupBuild() {
	 // build graph with accurate distances
	 // TODO
	cleanConnections();
	for(Point p: wallPoints) {
		for(Point p2: wallPoints) {
			boolean add = true;
			if(p.wall.equals(p2.wall)) {
				add = false;
			}
			
			for(Wall w1: allWalls) {
				if( intersect(p,p2,w1.p1,w1.p2) != null && !w1.equals(p.wall) && !w1.equals(p2.wall)) {
					add = false;
//					stroke(0,250,250);
//					line((int)p.x,(int)p.y,(int)p2.x,  (int)p2.y);
//					stroke(121,100,40);
//					intersect(p,p2,w1.p1,w1.p2).display();
//					break;
				}
			
			}
			if(add == true) {
				
				p.neighbors.put(p2, (double) dist(p,p2)  );//distance between two points
			}
		}			
	}
 lastPoint = null; 
}

void cleanConnections() {
	for(Point p: wallPoints)  {
		p.neighbors.clear();
	}
	
}


void initPlay() {
 mode = PLAY_MODE;
 

 
 resetPlayers();
}

void resetPlayers() {
 // reset player/enemy positions
 player = new Player();
 ghosts = new Mover[]{
   new Mover(new StandStill()),
   new Mover(new MoveTo(player)),
   new Mover(new MoveTo(mouse)),
   new Mover(new RandomMovement())
 };
}

void cleanupPlay() {
	mode = BUILD_MODE;
 	cleanConnections();
}

public void mousePressed() {
 if (mode == BUILD_MODE) {
   Point clicked = findPoint(mouseX, mouseY);
   if (mouseButton == LEFT) {
     // create new Point
     if (clicked == null) {
       clicked = new Point(mouseX, mouseY);
       if (lastPoint != null && lastPoint.wall == null) {
         new Wall(clicked, lastPoint);
       }
     }
     
     // whether point is new or pre-existing, select it for
     //   potential dragging/moving
     lastPoint = clicked;
   }
   if (mouseButton == RIGHT) {
     // remove Point or Wall
     if (clicked != null) {
       if (clicked.wall == null) {
         if (clicked == lastPoint) lastPoint = null;
       }
       else {
         clicked.wall.remove();
         // reset lastPoint if part of this wall
         if (lastPoint != null && lastPoint.wall == clicked.wall) {
           lastPoint = null;
         }
       }
     }
     // clicked on nothing; deselect lastPoint
     else if (lastPoint != null) lastPoint = null;
   }
 }
}

public void mouseDragged() {
 if (mode == BUILD_MODE && lastPoint != null) {
   // drag point
   lastPoint.x = mouseX;
   lastPoint.y = mouseY;
 }
}

// finds Point with 5 px of a given point
Point findPoint(float x, float y) {
 if (lastPoint != null &&
     dist(lastPoint.x, lastPoint.y, x, y) <= 5) {
   return lastPoint;
 }
 
 for (Point p : wallPoints) {
   if (dist(p.x, p.y, x, y) <= 5) return p;
 }
 
 return null;
}

class Point {	//TODO
	Map<Point, Double> neighbors = new HashMap<>();
 
 float x, y;
 Wall wall;
 
 Point() {
   x = random(0, width);
   y = random(0, height);
 }
 
 Point(float x, float y) {
   this.x = x;
   this.y = y;
 }
 
 void display() {
   display(0, 100, 100); // bright red
 }
 
 void display(int h, int s, int b) {
   stroke(h,s,b);
   strokeWeight(3);  // medium
   noFill();
   ellipse(x, y, 10, 10);
 }
 public String toString() {
     return "(" + x + "," + y + ")";
   }

 
}

class Wall {
 Point p1, p2;
 
 Wall() {
   p1 = new Point();
   p2 = new Point();
   p1.wall = this;
   p2.wall = this;
   addToSets();
 }
 
 Wall(Point a, Point b) {
   p1 = a;
   p2 = b;
   a.wall = this;
   b.wall = this;
   addToSets();
 }
 
 Wall(float x1, float y1, float x2, float y2) {
   this(new Point(x1, y1), new Point(x2, y2));
 }
 
 void addToSets() {
   allWalls.add(this);
   wallPoints.add(p1);
   wallPoints.add(p2);
 }
 
 void display() {
   stroke(0); // black
   strokeWeight(5);
   line(p1.x, p1.y, p2.x, p2.y);
 }
 
 void remove() {
   wallPoints.remove(p1);
   wallPoints.remove(p2);
   allWalls.remove(this);
 }

 // generates array code for this wall
 String arrayString() {
   return "{" + p1.x + "f," + p1.y + "f," + p2.x + "f," + p2.y + "f}";
 }
}

class Mover extends Point {
 MoveRule rule;
 float speed = 2.0f;
 float hue;
 
 Mover() {
   rule = new StandStill();
   hue = random(0, 360);
 }
 
 Mover(MoveRule rule) {
   this.rule = rule;
   hue = random(0, 360);
 }
 
 void move() {
   rule.move(this);
 }
 
 // moves in direction of p
 void moveTo(Point p) {
   moveTo(p.x, p.y);
 }
 
 // moves in direction of (x2, y2)
 void moveTo(float x2, float y2) {
   float dx = x2 - x;
   float dy = y2 - y;
   
   float d = dist(0, 0, dx, dy);
   
   // don't move
   if (d == 0) return;
   
   // scale down movement if necessary
   if (d > speed) {
     dx *= speed / d;
     dy *= speed / d;
   }
   
   Point target = new Point(x + dx, y + dy);
   Point crashPoint = crashCheck(target);
   
   // move to target if nothing blocks it
   if (crashPoint == null) {
     x += dx;
     y += dy;
   }
   // move halfway to blockage, unless within 1px already
   else if (dist(this, crashPoint) >= 1.0) {
     x = (x + crashPoint.x) / 2;
     y = (y + crashPoint.y) / 2;
   }
 }
 
 // returns nearest Point that moving to target would
 //   crash into, or null if movement is unblocked
 Point crashCheck(Point target) {
   Point nearest = null;
   // check all walls
   for (Wall w : allWalls) {
     Point wIntersect = intersect(this, target, w.p1, w.p2);
     // allow a 0.1 leeway around ends of lines
     if (wIntersect != null &&
         dist(wIntersect, w.p1) > 0.1 &&
         dist(wIntersect, w.p2) > 0.1) {
       
       // get closest result
       if (nearest == null ||
           dist(this, wIntersect) < dist(this, nearest)) {
         nearest = wIntersect;
       }
     }
   }
   return nearest;
 }
 
 @Override
 void display() {
   noStroke();
   fill(hue, 100, 100);
   ellipse(x, y, 10, 10);
 }
}

// interface allowing specification of movement rules
interface MoveRule {
 void move(Mover m);
}

class StandStill implements MoveRule {
 public void move(Mover m) {};
}

class RandomMovement implements MoveRule {
 public void move(Mover m) {
   float angle = random(0, TAU);
   
   m.moveTo(m.x + cos(angle) * m.speed,
            m.y + sin(angle) * m.speed);
 }
}

class MoveTo implements MoveRule {
 Point target;
 
 MoveTo(Point target) {
   this.target = target;
 }
 
 public void move(Mover m) {
   m.moveTo(target);
 }
}

class Player extends Mover {
 Player() {
   super(new MoveTo(mouse));
 }
 
 @Override
 void display() {
   noStroke();
   fill(hue, 100, 100); // light
   ellipse(x, y, 20, 20);
   fill(hue, 100, 50);  // darker
   ellipse(x, y, 3, 3);
 }
}
}