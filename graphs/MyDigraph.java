package graphs;

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class MyDigraph implements Digraph {
    private final int V;
    private int E = 0;
    private List<Integer>[] adj;

    public MyDigraph(int V){
        this.V = V;
        this.E = 0;
        this.adj = (List<Integer>[]) new List[V];

        for(int v = 0; v < V; v++)
            this.adj[v] = new LinkedList<Integer>();
    }

    public MyDigraph(Scanner sc){
        this(sc.nextInt()); // number of vertices. Constructor call must be the first statement in a constructor
        int numberOfEdges = sc.nextInt();

        for(int i = 0; i < numberOfEdges; i++){
            int v = sc.nextInt();
            int w = sc.nextInt();
            this.addEdge(v, w);
        }
        sc.close();
    }

    public int V(){ 
        return this.V; 
    }

    public int E(){ 
        return this.E; 
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        this.E++;
    }

    public Iterable<Integer> adj(int v){ 
        return this.adj[v]; 
    }

    public Digraph reverse(){
        Digraph reversed = new MyDigraph(this.V);

        for(int v = 0; v < this.V; v++){
            for(int w : this.adj(v)){
                reversed.addEdge(w, v);
            }
        }

        return reversed;
    }

    public String toString(){
        String s = this.V() + " vertices, " + this.E() + " edges\n";
        for(int v = 0; v < this.V(); v++){
            s += v + ": ";
            for(int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }

        return s;
    }
}