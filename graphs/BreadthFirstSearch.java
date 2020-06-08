package graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstSearch(Graph G, int s){
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.s = s;
        
        this.bfs(G, s);
    }

    private void bfs(Graph G, int s){
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.add(s);
        while(!queue.isEmpty()){
            int v = queue.remove();
            for(int w: G.adj(v)){
                if(!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
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