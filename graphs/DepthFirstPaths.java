package graphs;

import java.util.Stack;

public class DepthFirstPaths{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s){
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.s = s;
        
        this.dfs(G, s);
    }

    private void dfs(Graph G, int v){
        this.marked[v] = true;
        
        for(int w : G.adj(v)){
            if(!this.marked[w]){

                this.edgeTo[w] = v;
                this.dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int w){
        return this.marked[w];
    }

    public Iterable<Integer> pathTo(int v){
        if(!this.hasPathTo(v)) return null;
        
        Stack<Integer> path = new Stack<Integer>();
        path.push(this.s);
        for(int x = v; x != this.s; x = edgeTo[x]){
            path.push(x);
        }
        return path;
    }
}