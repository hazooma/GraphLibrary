import java.io.*; 
import java.util.*; 
  
class DisjointUnionSets 
{ 
    HashMap<StringBuffer,StringBuffer>  parent; 
    HashMap<StringBuffer,Integer> rank;
    StringBuffer [] arr ;
  
    // Constructor 
    public DisjointUnionSets(StringBuffer [] arr) 
    { 
        rank = new HashMap<StringBuffer, Integer>(); 
        parent = new   HashMap<StringBuffer,StringBuffer>();
        this.arr = arr; 
        makeSet(); 
    } 
  
    // Creates n sets with single item in each 
    void makeSet() 
    { 
        for (int i=0; i<arr.length; i++) 
        { 
            // Initially, all elements are in 
            // their own set.
        	StringBuffer str = arr[i];
            parent.put(str, str) ; 
            rank.put(str, 0);
        } 
    } 
  
    // Returns representative of x's set 
    StringBuffer find(StringBuffer x) 
    { 
        // Finds the representative of the set 
        // that x is an element of 
        if (parent.get(x)!=x) 
        { 
            // if x is not the parent of itself 
            // Then x is not the representative of 
            // his set, 
            parent.put(x, find(parent.get(x))); 
  
            // so we recursively call Find on its parent 
            // and move i's node directly under the 
            // representative of this set 
        } 
  
        return parent.get(x); 
    } 
  
    // Unites the set that includes x and the set 
    // that includes x 
    void union(StringBuffer x, StringBuffer y) 
    { 
        // Find representatives of two sets 
    	StringBuffer xRoot = find(x);
    	StringBuffer yRoot = find(y); 
  
        // Elements are in the same set, no need 
        // to unite anything. 
        if (xRoot.toString().equals( yRoot)) 
            return; 
  
         // If x's rank is less than y's rank 
        if (rank.get(xRoot) < rank.get(yRoot)) 
  
            // Then move x under y  so that depth 
            // of tree remains less 
            parent.put(xRoot ,yRoot); 
  
        // Else if y's rank is less than x's rank 
        else if (rank.get(yRoot) < rank.get(xRoot)) 
  
            // Then move y under x so that depth of 
            // tree remains less 
            parent.put(yRoot , xRoot); 
  
        else // if ranks are the same 
        { 
            // Then move y under x (doesn't matter 
            // which one goes where) 
            parent.put(yRoot, xRoot); 
  
            // And increment the the result tree's 
            // rank by 1 
            rank .put(xRoot, rank.get(xRoot)+1); 
        } 
    } 
} 