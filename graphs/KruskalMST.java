package graphs;

import java.util.LinkedList;
import java.util.Queue;

public class KruskalMST implements IMST {
    private Queue<Edge> mst;
    private double weight;

    public KruskalMST(EdgeWeightedGraph G){
        mst = new LinkedList<>();
        MinPQ<Edge> pq = new MinPQ<Edge>(G.edgesArray());
        UF uf = new UF(G.V());

        while(!pq.isEmpty() && mst.size() < G.V() - 1){
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);

            if(uf.find(v) == uf.find(w))
                continue;

            uf.union(v, w);
            mst.add(e);
            weight += e.weight();
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        return weight;
    }
}