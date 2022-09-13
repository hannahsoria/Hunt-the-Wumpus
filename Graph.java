/**
 * Author: Hannah Soria
 * Date: 5/4/2022
 * File: Vertex.java
 * Section lab C, Lecture A
 * Project 10: Hunt the Wumpus
 * CS231 Spring 2022
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {
    private ArrayList <Vertex> vertex;
    private int size;

    //constructor of the graph
    public Graph(){
        this.vertex = new ArrayList<Vertex>();
        this.size = 0;
    }

    //returns the number of vertices in the graph.
    public int vertexCount(){
        return vertex.size();
    }

    public ArrayList<Vertex> getVertices(){
        return vertex;
    }

    //return true if the query Vertex is in the graph's vertex list.
    public boolean inGraph(Vertex query){
        for (int i = 0; i < vertex.size(); i++){
            if (vertex.get(i) == query){
                return true;
            }
        }
        return false;
    }

    //adds v1 and v2 to the graph (if necessary) and adds an edge connecting v1 to v2, creating a uni-directional link.
    public void addUniEdge(Vertex v1, Vertex v2){
        if (inGraph(v1) == false){
            vertex.add(v1);
            size++;
        }
        if (inGraph(v2) == false){
            vertex.add(v2);
            size++;
        }
        v1.connect(v2, null);
    }

    //adds v1 and v2 to the graph (if necessary), adds an edge connecting v1 to v2, and adds a second edge connecting v2 to v1, creating a bi-directional link.
    public void addBiEdge(Vertex v1, Vertex v2){
        if (inGraph(v1) == false){
            vertex.add(v1);
            size++;
        }
        if (inGraph(v2) == false){
            vertex.add(v2);
            size++;
        }
        v1.connect(v2, null);
        v2.connect(v1, null);
    }

    //writes a toString
    public String toString(){
        String s = "";
        for (int i = 0; i < vertexCount(); i++){
            s += this.vertex.get(i).toString() + "\n";
        } 
        return s;
    }

    //implements a single-source shortest-path algorithm for the Graph, Dijkstra's algorithm.
    //i recieved help from a TA
    public void shortestPath(Vertex v0){
        for (int i = 0; i < this.size; i++){
            this.vertex.get(i).setVisit(false);
            this.vertex.get(i).setCost(1e+7);
            this.vertex.get(i).setParent(null);
        }

        PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
        v0.setCost(0);
        q.add(v0);
        while (!q.isEmpty()){
            Vertex v = q.poll();

            if(v.getVisit()){
                continue;
            }
            v.setVisit(true);

            for (Vertex w: v.getNeighbors()){
                double distance = v.distance(w);
                if (!w.getVisit() && v.getCost() + distance < w.getCost()){
                    w.setCost(v.getCost() + distance);
                    w.setParent(v);
                    q.add(w);
                }
            } 
        }
    }

    //adds vertex to the arrayList
    public void add(Vertex v){
        vertex.add(v);
    }

    //tests the functions
    public static void main(String [] argv){
        Graph test = new Graph();
        Vertex v1 = new Vertex(5, 5, false);
        Vertex v2 = new Vertex(10, 10, false);
        Vertex v3 = new Vertex(2, 2, false);
        Vertex v4 = new Vertex(10, 10, false);
        Vertex v5 = new Vertex(2, 2, false);
        test.addBiEdge(v1, v2);
        test.addBiEdge(v1, v3);
        test.addBiEdge(v2, v3);
        test.addBiEdge(v3, v5);
        test.addBiEdge(v4, v2);
        //test.addUniEdge(v2, v3);
        System.out.println("test vertex count: " + test.vertexCount());
        System.out.println("test get vertices: " + test.getVertices());
        System.out.println("test in graph: " + test.inGraph(v1));
        System.out.println(test.toString());
        test.shortestPath(v1);
        System.out.println(test.toString());
        test.shortestPath(v3);
        System.out.println(test.toString());
    }
}
