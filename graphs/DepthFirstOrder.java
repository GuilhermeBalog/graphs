package graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstOrder {
    private boolean[] marked;

    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G){
        pre         = new LinkedList<Integer>();
        post        = new LinkedList<Integer>();
        reversePost = new Stack<Integer>();
        marked      = new boolean[G.V()];
        
        for(int v = 0; v < G.V(); v++){
            if(!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v){
        pre.add(v);

        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w]) dfs(G, w);
        }
        post.add(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }
    
    public Iterable<Integer> post(){
        return post;
    }
    
    public Iterable<Integer> reversePost(){
        return reversePost;
    }
    
}