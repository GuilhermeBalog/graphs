package graphs;

public interface ShortestPaths {
    /**
     * distance from s to v , ∞ if no path
     * @param v target vertex
     * @return distance from source to v
     */
    double distTo(int v);

    boolean hasPathTo(int v);
    Iterable<DirectedEdge> pathTo(int v);
}