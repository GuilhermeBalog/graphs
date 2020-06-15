package graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private List<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        this.adj = (List<DirectedEdge>[]) new List[V];

        for(int v = 0; v < V; v++){
            this.adj[v] = new LinkedList<>();
        }
    }

    public EdgeWeightedDigraph(Scanner sc){
        this(sc.nextInt());
        int numberOfEdges = sc.nextInt();

        for(int i = 0; i < numberOfEdges; i++){
            int v = sc.nextInt();
            int w = sc.nextInt();
            double weight = sc.nextDouble();

            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    public int V(){ 
        return this.V; 
    }

    public int E(){ 
        return this.E; 
    }

    public void addEdge(DirectedEdge e){
        
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v){
        return this.adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        Set<DirectedEdge> edges = new TreeSet<>();

        for(int v = 0; v < V; v++){
            for(DirectedEdge e : adj[v]){
                edges.add(e);
            }
        }

        return edges;
    }

    public String toString(){
        String s = this.V() + " vertices, " + this.E() + " edges\n";
        for(int v = 0; v < this.V(); v++){
            s += v + ": ";
            for(DirectedEdge e : this.adj(v))
                s += "(" + e + ") ";
            s += "\n";
        }

        return s;
    }
}