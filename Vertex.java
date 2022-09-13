/**
 * Author: Hannah Soria
 * Date: 5/4/2022
 * File: Vertex.java
 * Section lab C, Lecture A
 * Project 10: Hunt the Wumpus
 * CS231 Spring 2022
 */

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Vertex implements Comparable <Vertex>{
    private ArrayList<Vertex> vertex; 
    private int xCor;
    private int yCor;
    private boolean visible;
    private double cost;//also cost
    private boolean beenVisited;
    private Vertex parent;

    //vertex constructor
    public Vertex(int x, int y, boolean visible){
        this.vertex = new ArrayList<Vertex>();
        this.xCor = x;
        this.yCor = y;
        this.visible = visible;
        this.visible = false;
        this.cost = 1e+7;
        this.parent = null;
    }

    //gives the vertex their direction
    public enum Direction{ // https://www.w3schools.com/java/java_enums.asp
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    //returns the Euclidean distance between this vertex and the other vertex based on their x and y positions.
    public double distance( Vertex other ){
        int otherX = other.getX();
        int otherY = other.getY();
        double totalXdist = xCor - otherX;
        double totalYdist = yCor - otherY;
        totalXdist = (double)Math.pow(totalXdist, 2);
        totalYdist = (double)Math.pow(totalYdist, 2);
        double d = Math.sqrt(totalXdist + totalYdist);
        return d;
    }

    //updates this vertex' adjacency list/map so that it connects with the other Vertex. This is a uni-directional link.
    public void connect(Vertex other, Vertex.Direction dir){
        vertex.add(other);
    } 

    //returns the Vertex at position (x, y) if the Vertex is in the adjacency list, otherwise null.
    public Vertex getNeighbor(int x, int y){
        for (int i = 0; i < vertex.size(); i++){
                if (vertex.get(i).getX() == x && vertex.get(i).getY() == y){
                    return vertex.get(i);
                }
        }
        return null;
    }
    
    //returns an ArrayList of type Vertex which contains all of this Vertex' neighbors.
    public ArrayList<Vertex> getNeighbors(){
        return vertex;
    }
    
    //returns the number of connected vertices.
    public int numNeighbors(){
        return vertex.size();
    }

    //returns a String containing (at least) the number of neighbors, this Vertex' cost, and the marked flag.
    public String toString(){
        String s = "XCOR: " + xCor + " YCOR: " + yCor + "\nthe number of neighbors: " + numNeighbors()+ "\nThis vertex' cost: " + cost + "\nbeen visitied: " + getVisit();
        return s;
    }

    //returns x coordinate
    public int getX(){
        return xCor;
    }
    
    //sets x coordinate
    public void setX(int x){
        xCor = x;
    }

    //gets y coordinate
    public int getY(){
        return yCor;
    }

    //sets y coordinate
    public void setY(int y){
        yCor = y;
    }

    //returns visibility
    public boolean getVisible(){
        return visible;
    }

    //sets visiblity
    public void setVisible(boolean newVisit){
        visible = newVisit;
    }

    //gets the distance / cost
    public double getCost(){
        return cost;
    }

    //sets the distance / cost
    public void setCost(double newDist){
        cost = newDist;
    }

    //gets if it has been visited
    public boolean getVisit(){
        return beenVisited;
    }

    //sets the visit
    public void setVisit(boolean newVisit){
        beenVisited = newVisit;
    }

    //gets the parent
    public Vertex getParent(){
        return parent;
    }

    //sets the parent
    public void setParent(Vertex newParent){
        parent = newParent;
    }

    //returns a value < 0 if this vertex comes before other, 0 if this is equal to other, and a value > 0 if this comes after other
    public int compareTo(Vertex other) {
        if (other.getCost() == this.getCost()){
            return 0;
        } else if (other.getCost() > this.getCost()){
            return -1;
        } else {
            return 1;
        }
    }

    //returns true if the x and y positions of the two vertices match
    public static boolean matchPosition( Vertex a, Vertex b ){
        if (a.getX() == b.getX() && a.getY() == b.getY()){
            return true;
        }
        return false;
    }

    //draws the rooms
    public void draw(Graphics g, int scale){
        if (!this.visible){
            return;
        }
        int xpos = (int)this.getX()*scale;
        int ypos = (int)this.getY()*scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;

        // draw rectangle for the walls of the room
        if (this.getCost() <= 2){
            // wumpus is nearby
            g.setColor(Color.CYAN);
        }
            
        else{ 
            // wumpus is not nearby
            g.setColor(Color.BLUE);
        }
            
        
        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
        
        // draw doorways as boxes
        g.setColor(Color.WHITE);
        if (this.getNeighbor( this.getX(), this.getY()-1 ) != null ){
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        }
        if (this.getNeighbor( this.getX(), this.getY()+1 ) != null ){
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), eighth, eighth + sixteenth);
        }
        if (this.getNeighbor( this.getX()-1, this.getY() ) != null){
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        }
        if (this.getNeighbor( this.getX()+1, this.getY() ) != null){
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth,  eighth + sixteenth, eighth);
        }
    }

    //tests the functions
    public static void main(String [] argv){
        Vertex test = new Vertex(10, 10, true);
        System.out.println(test);
        Vertex test2 = new Vertex( 15, 15, true);
        System.out.println("test of getX: " + test.getX());
        System.out.println("test of setX: " + test.getY());
        test.setX(5);
        System.out.println("test of after setX: " + test.getX());
        test.setY(5);
        System.out.println("test of after setY: " + test.getY());
        System.out.println("test of getVisible: " + test.getVisible());
        test.setVisible(false);
        System.out.println("test of after setVisible: " + test.getVisible());
        System.out.println("test of getDistance: " + test.getCost());
        test.setCost(20.0);
        System.out.println("test of after setDistance: " + test.getCost());
        System.out.println("test of getVisit: " + test.getVisit());
        test.setVisit(true);
        System.out.println("test of after setVisit: " + test.getVisit());
        System.out.println("test of getParent: " + test.getParent());
        test.setParent(test2);;
        System.out.println("test of after setParent: " + test.getParent());
        System.out.println("test of distance: " + test.distance(test2));
        test.connect(test2, Direction.EAST);
        System.out.println("test of connect and getNeighnor: " + test.getNeighbor(15, 15));
        System.out.println("test of getNeighbors: " + test.getNeighbors());
        System.out.println("test numNeighbors: " + test.numNeighbors());
        System.out.println(test);
    }
}
