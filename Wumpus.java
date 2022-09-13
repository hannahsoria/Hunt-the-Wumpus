/**
 * Author: Hannah Soria
 * Date: 5/4/2022
 * File: Wumpus.java
 * Section lab C, Lecture A
 * Project 10: Hunt the Wumpus
 * CS231 Spring 2022
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

public class Wumpus {
    private Vertex home;
    private boolean vis;

    //wumpus constructor
    public Wumpus(Vertex v){
        this.home = v;
        this.vis = false;
    }

    //returns if the wumpus is visible
    public boolean getVisible(){
        return vis;
    }

    //sets teh wumpus to a new bool
    public void setVisible(boolean newVis){
        vis = newVis;
    }

    //returns the wumpus home
    public Vertex getHome(){
        return home;
    }

    //sets the wumpus home
    public void setHome(Vertex newHome){
        this.home = newHome;
    }

    //draws the wumpus
    public void draw(Graphics g, int scale){
        if (vis== false){
            return;
        }
        int xpos = home.getX()*scale;
        int ypos = home.getY()*scale;
        if (home.getVisible()){
            g.setColor(Color.yellow);
        }
        Image wumpus = Toolkit.getDefaultToolkit().getImage("wump.png");
        g.drawImage(wumpus, xpos, ypos, null);
    }

    //tests the functions
    public static void main(String[]args){ 
        Vertex wumpusVert = new Vertex(2, 2, false);
        Vertex newWumpusVert = new Vertex(5, 5, false);
        Wumpus wumpus = new Wumpus(wumpusVert);
        System.out.println("test of gethome: " + wumpus.getHome());
        wumpus.setHome(newWumpusVert);
        System.out.println("test of sethome: " + wumpus.getHome());
    }
}
