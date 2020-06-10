package graphs;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LazyPrimMST implements IMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private PriorityQueue<Edge> pq;
    private double weight;

    public LazyPrimMST(EdgeWeightedGraph G){
        pq = new PriorityQueue<>();
        marked = new boolean[G.V()];
        mst = new LinkedList<>();

        visit(G, 0);
        while(!pq.isEmpty()){
            Edge e = pq.remove();

            int v = e.either();
            int w = e.other(v);

            if(marked[v] && marked[w])
                continue; // Ineligle

            mst.add(e);
            weight += e.weight();

            if(!marked[v]) visit(G, v);
            if(!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;
        for(Edge e : G.adj(v)){
            if(!marked[e.other(v)]){
                pq.add(e);
            }
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        return weight;
    }
}