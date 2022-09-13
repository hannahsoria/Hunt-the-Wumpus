/**
 * Author: Hannah Soria
 * Date: 5/4/2022
 * File: Hunter.java
 * Section lab C, Lecture A
 * Project 10: Hunt the Wumpus
 * CS231 Spring 2022
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Hunter {
    private Vertex location;
    private int armed; //1 = is armed, -1 = is not armed

    //hunter constructor
    public Hunter(Vertex locate){
        this.location = locate;
        this.location.setVisible(true);
        this.armed = -1;
    }

    //returns the location of the hunter
    public Vertex getLocation(){
        return location;
    }

    //sets the location of the hunter
    public void setLocation(Vertex newlocation){
        location = newlocation;
    }

    //returns if they hunter is armed
    public int getArmed(){
        return armed;
    }

    //sets the status of if the wumpus is armed
    public void setArmed(int changeArmed){
        this.armed = changeArmed;
    }

    //moves the hunter north, south, east, west
    public void move(Vertex.Direction dir){
        if (dir == Vertex.Direction.NORTH){
            if(this.location.getNeighbor(location.getX(), location.getY()-1)!=null){
                this.location = this.location.getNeighbor(location.getX(), location.getY()-1);
            }
        }
        if (dir == Vertex.Direction.SOUTH){
            if(this.location.getNeighbor(location.getX(), location.getY()+1)!=null){
                this.location = this.location.getNeighbor(location.getX(), location.getY()+1);
            }
        }
        if (dir == Vertex.Direction.EAST){
            if(this.location.getNeighbor(location.getX()-1, location.getY())!=null){
                this.location = this.location.getNeighbor(location.getX()-1, location.getY());
            }
        }
        if (dir == Vertex.Direction.WEST){
            if(this.location.getNeighbor(location.getX()+1, location.getY())!=null){
                this.location = this.location.getNeighbor(location.getX()+1, location.getY());
            }
        }
    }
    
    //draws the hunter
    public void draw(Graphics g, int scale){
        int xPos = location.getX() * scale;
        int yPos = location.getY() * scale;
        Image hunter = Toolkit.getDefaultToolkit().getImage("hunt.png");
        g.drawImage(hunter, xPos + 10 , yPos , null);
    }

    //tests the functions
    public static void main(String[]args){ 
        Vertex huntVert = new Vertex(5, 5, true);
        Vertex newHuntVert = new Vertex(10, 10, true);
        Hunter hunter = new Hunter(huntVert);
        System.out.println("test of getlocation: " + hunter.getLocation());
        hunter.setLocation(newHuntVert);
        System.out.println("test of setlocation: " + hunter.getLocation());
        System.out.println("test of isarmed: " + hunter.getArmed());
        hunter.setArmed(-1);
        System.out.println("test of setarmed: " + hunter.getArmed());
    }
}


