package graphs;

public class KosarajuStrongComponents {
    private boolean[] marked;
    private int[] id;
    private int count = 0;

    public KosarajuStrongComponents(Digraph G){
        marked = new boolean[G.V()];
        id     = new int[G.V()];

        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for(int s : order.reversePost()){
            if(!marked[s]){
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v){
        this.marked[v] = true;
        this.id[v] = count;
        for(int w: G.adj(v)){
            if(!this.marked[w]){
                this.dfs(G, w);
            }
        }
    }

    public boolean areStronglyConnected(int v, int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }
}