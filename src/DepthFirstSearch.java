
public class DepthFirstSearch {
    private boolean[] marked;
    private int count = 0;

    public DepthFirstSearch(Graph G, int s){
        this.marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v){
        this.marked[v] = true;
        count++;
        for(int w : G.adj(v)){
            if(!marked[w]) this.dfs(G, w);
        }
    }

    public int count(){
        return this.count;
    }

    public boolean marked(int w){
        return this.marked[w];
    }
}