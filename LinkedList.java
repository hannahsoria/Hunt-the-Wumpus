/**
 * Author: Hannah Soria
 * Date: 3/7/2022
 * File: LinkedzList.java
 * Section lab C, Lecture A
 * Lab: 5 Linked Lists
 * CS231 Spring 2022
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;    // defines the Iterator interface

//Meredith helped me with this
public class LinkedList <T> implements Iterable <T>{ 

    private int size;
    private Node<T> head;

    private class Node <T>{
        private Node <T> next;
        private T data;

        // constructor that initializes next to null and the container field to item.
        public Node(T item){ 
            data = item;
        }
        
        //public T getThing()
        public T getThing(){ 
            return data;
        }

        //sets next to the given node.
        public void setNext(Node<T> n){ 
            next = n;
        }

        //returns the next field.
        public Node<T> getNext(){ 
            return next;
        }
    }

    //return a new LLIterator with head passed to the constructor, shown as the following code
    public Iterator <T> iterator(){ 
        return new LLIterator(this.head);
    }

    private class LLIterator implements Iterator <T> {
        
        private Node<T> nextNode;

        //the constructor for the LLIterator given the head of a list.
        public LLIterator(Node<T> head){ 
            nextNode = head;
        }

        //returns the next item in the list, which is the item contained in the current node.
        public T next() { 
            if (hasNext()){
                T data = nextNode.getThing();
                nextNode = nextNode.getNext();
                return data;
            } else{
                return null;
            }
        }

        //returns true if there are still values to traverse (if the current node reference is not null).
        public boolean hasNext(){ 
            if (nextNode != null){
                return true;
            } else {
                return false;
            }
        }
    
        //does nothing. Implementing this function is optional for an Iterator.
        public void remove(){ 
        }
    }

    // constructor that initializes the fields so it is an empty list.
    public LinkedList(){
        size = 0;
        head = null;
    }

    //resets the fields so it is an empty list
    public void clear(){ 
        size = 0;
        head = null;
    }

    //returns the size of the list
    public int size(){ 
        return size;
    }

    //inserts the item at the beginning of the list
    public void addFirst(T item){ 
        Node <T> newNode = new Node <T>(item);
        if (head == null){
            //newNode.setNext(head.getNext());
            head = newNode;
            size++;
        }else{
            newNode.setNext(head);
            head = newNode;
            size++;
        }
    }

    //appends the item at the end of the list
    public void addLast(T item){ 
        Node<T> temp1 = head;
        Node <T> newNode = new Node <T> (item);
        if (head == null){
            head = newNode;
            size++;
        } else {
            while (temp1.getNext() != null ){
            temp1 = temp1.getNext();
            }
            temp1.setNext(newNode);
            size++;
        } 
    }

    //inserts the item at the specified poistion in the list
    public void add (int index, T item){ 
        Node<T> temp2 = head;
        Node<T> newNode = new Node<T>(item);
        if (head == null){
            head = newNode;
            size++;
        }
        else if(index == 0){
            this.addFirst(item);
        } 

        else if (index == size){
            this.addLast(item);
        }
        else {
            for (int i = 0; i < size - 1; i++){
                if (index - 1 == i){
                    newNode.setNext(temp2.getNext());
                    temp2.setNext(newNode);
                }
                if (temp2 != null){
                    temp2 = temp2.getNext();
                }
                
            }
            size++;
        }
    }

    //removes the item at the specified position in the list
    public T remove (int index){ 
        Node<T> current = head.getNext();
        Node<T> previous = head;
        int count = 0;
        if (index == 0){
            if(head.getNext()!= null){
                head = head.getNext();
            }
            else{
                head = null;
            }
        }
        while (current != null){
            if (count == index - 1){
                if (index == size){
                    current = null;
                }
                else{
                    previous.setNext(current.getNext());
                }
            }
            previous = current;
            current = current.getNext();
            count++;
        }
        size--;
        return null;
    }

    //bender helped me with this
    //returns an ArrayList of the list contents in order.
    public ArrayList<T> toArrayList(){ 
        ArrayList<T> array = new ArrayList<T>();
        for (T item:this){
            array.add(item);
        }
        return array;
    }

    //returns an ArrayList of the list contents in shuffled order
    public ArrayList<T> toShuffledList(){ 
        ArrayList<T> array = toArrayList();
        Collections.shuffle(array);
        return array;
    }   
}
