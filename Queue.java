//Code from "Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne"
//https://algs4.cs.princeton.edu/13stacks/Queue.java.html

import java.util.*;

public class Queue<Item> implements Iterable<Item> 
{
    private Node<Item> first;    //Beginning of queue
    private Node<Item> last;     //End of queue
    private int n;               //Number of elements on queue

    //Helper linked list class
    private static class Node<Item> 
    {
        private Item item;
        private Node<Item> next;
    }

    //Initialize empty queue
    public Queue() 
    {
        first = null;
        last  = null;
        n = 0;
    }

    //Return true if no items in queue
    public boolean isEmpty() 
    {
        return first == null;
    }

    //Return number of objects in queue
    public int size() 
    {
        return n;
    }

    //Look at most recently added item
    public Item peek() 
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    //Add item to queue
    public void enqueue(Item item) 
    {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        
        if(isEmpty())
        	first = last;
        else
        	oldlast.next = last;
        
        n++;
    }

    //Removes most recently added item
    public Item dequeue() 
    {
        if (isEmpty()) 
        	throw new NoSuchElementException("Queue underflow");
        
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())
        	last = null;
        
        return item;
    }

    //Print queue
    public String toString() 
    {	
        StringBuilder s = new StringBuilder();
        for (Item item : this) 
        {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    } 

   
    //Returns an iterator that iterates over the items in this queue in FIFO order.
    public Iterator<Item> iterator()  
    {
        return new LinkedIterator(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> 
    {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) 
        {
            current = first;
        }

        public boolean hasNext()
        {
        	return current != null;
    	}
        
        public void remove()
        {
        	throw new UnsupportedOperationException();	
        }

        public Item next() 
        {
            if (!hasNext())
            	throw new NoSuchElementException();
            
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}