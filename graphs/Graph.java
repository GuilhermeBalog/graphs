package graphs;
/**
 * Graph API, from Algorithms, 4th Edition, pg. 522
 */

public interface Graph{
    int V();
    int E();
    void addEdge(int v, int w);
    Iterable<Integer> adj(int v);
}