/**
 * Author: Hannah Soria
 * Date: 5/4/2022
 * File: Landscape.java
 * Section lab C, Lecture A
 * Project 10: Hunt the Wumpus
 * CS231 Spring 2022
 */

import java.awt.Graphics;

public class Landscape {
    private int h;
    private int w;
    LinkedList<Vertex> vertex;
    Graph graph;
    Hunter hunt;
    Wumpus wump;

    //constuctor
    public Landscape(int w, int h){ 
        this.h = h;
        this.w = w;
        graph =  new Graph();
        vertex = new LinkedList<>();
    }

    //sets the hunter
    public void setHunter(Hunter v){
        this.hunt = v;
    }

    //sets the wumpus
    public void setWumpus(Wumpus v){
        this.wump = v;
    }

    //return the height of the Landscape.
    public int getHeight(){ 
        return h;
    }

    //return the width of the Landscape.
    public int getWidth(){ 
        return w;
    }

    //return a string of the number of vertices
    public String toString(){ 
        String s = "";
        s += "Number of vertices: " + vertex.size();
        return s;
    }

    //draws the vertics to the grpah and the hunter and the wumpus.
    public void draw( Graphics g, int scale ){ 
        for (Vertex v: graph.getVertices()){
            v.draw(g, 64);
            v.setCost(hunt.getLocation().distance(wump.getHome()));
            hunt.getLocation().setCost(hunt.getLocation().distance(wump.getHome()));
            if (hunt.getLocation().getCost() <= 2){
                v.setCost(3);
            }
        }
        //draws the hunter and wumpus
        this.hunt.draw(g, 65);
        this.wump.draw(g,65);
    }

    //finds the cost of all of the vertices to the wumpus
    public void wumpCost(){
        for (Vertex v: graph.getVertices()){
            v.setCost(v.distance(wump.getHome()));
        }
    }

    //adds a vertex to the graph
    public void addBackgroundAgent(Vertex v){
        graph.add(v);
    }

    //tests the functions
    public static void main(String[]args){  
        Landscape scape = new Landscape(5, 5);
        System.out.println("test of getHeight: " + scape.getHeight());
        System.out.println("test of getWidth: " + scape.getWidth());
        System.out.println("test of toString: " + scape.toString());
        
    }
}


