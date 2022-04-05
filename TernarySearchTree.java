//Code from "Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne"
//https://algs4.cs.princeton.edu/52trie/TST.java.html

public class TernarySearchTree<Value> 
{
    private int n;              // size
    private Node<Value> root;   // root of TST

    private static class Node<Value> 
    {
        private char c;                        // character
        private Node<Value> left, mid, right;  // left, middle, and right subtries
        private Value val;                     // value associated with string
    }
    
    //Initializes an empty string symbol table.
    public TernarySearchTree()
    {}

    //Return number of key-value pairs
    public int size() 
    {
        return n;
    }

    //Checks table to see if key is in it
    public boolean contains(String key) 
    {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        
        return get(key) != null;
    }

    //Returns value associated with key
    public Value get(String key) 
    {
        if (key == null) 
        	throw new IllegalArgumentException("calls get() with null argument");
        if (key.length() == 0) 
        	throw new IllegalArgumentException("key must have length >= 1");
        
        Node<Value> x = get(root, key, 0);
        if (x == null) 
        	return null;
        
        return x.val;
    }

    //Return subtree corresponding to given key
    private Node<Value> get(Node<Value> x, String key, int d) 
    {
        if (x == null) 
        	return null;
        if (key.length() == 0)
        	throw new IllegalArgumentException("key must have length >= 1");
        
        char c = key.charAt(d);
        if(c < x.c)
        	return get(x.left,  key, d);
        else if(c > x.c)
        	return get(x.right, key, d);
        else if(d < key.length() - 1)
        	return get(x.mid,   key, d+1);
        else
        	return x;
    }

    //Inserts key-value pair into table. Overwrites if key-value pair already exists
    public void put(String key, Value val) 
    {
        if (key == null)
            throw new IllegalArgumentException("calls put() with null key");
        if (!contains(key)) 
        	n++;
        else if(val == null) 
        	n--;       // delete existing key
        
        root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) 
    {
        char c = key.charAt(d);
        if (x == null) 
        {
            x = new Node<Value>();
            x.c = c;
        }
        if(c < x.c)
        	x.left = put(x.left,  key, val, d);
        else if (c > x.c)
        	x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)
        	x.mid = put(x.mid,   key, val, d+1);
        else
        	x.val = val;
        return x;
    }

    //Returns string in symbol table with longest prefix of {@code query} or {@code null} if no string exists
    public String longestPrefixOf(String query) 
    {
        if (query == null)
            throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
        if (query.length() == 0)
        	return null;
        
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length())
        {
            char c = query.charAt(i);
            if(c < x.c)
            	x = x.left;
            else if (c > x.c)
            	x = x.right;
            else 
            {
                i++;
                if (x.val != null)
                	length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    //Returns all keys in symbol table
    public Iterable<String> keys() 
    {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    //Returns all keys with specific prefix
    public Iterable<String> keysWithPrefix(String prefix) 
    {
        if (prefix == null)
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        
        Queue<String> queue = new Queue<String>();
        Node<Value> x = get(root, prefix, 0);
        
        if (x == null)
        	return queue;
        if (x.val != null)
        	queue.enqueue(prefix);
        
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    //All keys in subtree rooted at x with given prefix
    private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) 
    {
        if (x == null)
        	return;
        collect(x.left, prefix, queue);
        if (x.val != null)
        	queue.enqueue(prefix.toString() + x.c);
       
        collect(x.mid, prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length()-1);
        collect(x.right, prefix, queue);
    }

    //Returns keys in table that match {@code pattern}
    public Iterable<String> keysThatMatch(String pattern) 
    {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }
 
    private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, Queue<String> queue) 
    {
        if (x == null) return;
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) collect(x.left, prefix, i, pattern, queue);
        if (c == '.' || c == x.c) {
            if (i == pattern.length() - 1 && x.val != null) queue.enqueue(prefix.toString() + x.c);
            if (i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i+1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > x.c) collect(x.right, prefix, i, pattern, queue);
    }
}