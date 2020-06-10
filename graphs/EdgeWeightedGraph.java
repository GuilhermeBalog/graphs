package graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private List<Edge>[] adj;

    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        this.adj = (List<Edge>[]) new List[V];

        for(int v = 0; v < V; v++){
            this.adj[v] = new LinkedList<>();
        }
    }

    public EdgeWeightedGraph(Scanner sc){
        this(sc.nextInt());
        int numberOfEdges = sc.nextInt();

        for(int i = 0; i < numberOfEdges; i++){
            int v = sc.nextInt();
            int w = sc.nextInt();
            double weight = sc.nextDouble();

            addEdge(new Edge(v, w, weight));
        }
    }

    public int V(){ 
        return this.V; 
    }

    public int E(){ 
        return this.E; 
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);

        adj[v].add(e);
        adj[w].add(e);

        E++;
    }

    public Iterable<Edge> adj(int v){
        return this.adj[v];
    }

    public Iterable<Edge> edges(){
        Set<Edge> edges = new TreeSet<>();

        for(int v = 0; v < V; v++){
            for(Edge e : adj[v]){
                edges.add(e);
            }
        }

        return edges;
    }

    public Edge[] edgesArray(){
        Set<Edge> edges = new TreeSet<>();

        for(int v = 0; v < V; v++){
            for(Edge e : adj[v]){
                edges.add(e);
            }
        }

        Edge[] a = new Edge[edges.size()];

        return edges.toArray(a);
    }

    public String toString(){
        String s = this.V() + " vertices, " + this.E() + " edges\n";
        for(int v = 0; v < this.V(); v++){
            s += v + ": ";
            for(Edge e : this.adj(v))
                s += e + " ";
            s += "\n";
        }

        return s;
    }
}