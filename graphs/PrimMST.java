package graphs;

import java.util.HashSet;
import java.util.Set;

public class PrimMST implements IMST {
    
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G){
        edgeTo = new Edge[G.V()];
        marked = new boolean[G.V()];
        pq     = new IndexMinPQ<>(G.V());
        distTo = new double[G.V()];

        for(int v = 0; v < G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while(!pq.isEmpty())
            visit(G, pq.delMin());
    }

    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;

        for(Edge e : G.adj(v)){
            int w = e.other(v);
            if(marked[w]) 
                continue;
            
            if(e.weight() < distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.weight();

                if(pq.contains(w)){
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public Iterable<Edge> edges(){
        Set<Edge> edges = new HashSet<>();

        for(int i = 1; i < edgeTo.length; i++){
            edges.add(edgeTo[i]);
        }

        return edges;
    }

    public double weight(){
        double weight = 0;

        for(int i = 1; i < edgeTo.length; i++){
            weight += edgeTo[i].weight();
        }

        return weight;
    }
}