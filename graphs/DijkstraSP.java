package graphs;

import java.util.Deque;
import java.util.LinkedList;

public class DijkstraSP implements ShortestPaths{
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s){
        this.distTo = new double[G.V()];
        this.edgeTo = new DirectedEdge[G.V()];
        this.pq     = new IndexMinPQ<>(G.V());

        for(int i = 0; i < G.V(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        pq.insert(s, 0.0);
        while(!pq.isEmpty())
            relax(G, pq.delMin());
    }

    private void relax(EdgeWeightedDigraph G, int v){
        for(DirectedEdge e: G.adj(v)){
            int w = e.to();

            if(distTo[v] + e.weight() < distTo[w]){
                edgeTo[w] = e;
                distTo[w] = distTo[v] + e.weight();

                if(pq.contains(w)) 
                    pq.changeKey(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);

            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)) return null;

        Deque<DirectedEdge> path = new LinkedList<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.addFirst(e);

        return path;
    }
}