public class ConnectedComponents {
    private boolean[] marked;
    private int[] id;
    private int count = 0;

    public ConnectedComponents(Graph G){
        this.marked = new boolean[G.V()];
        this.id     = new int[G.V()];

        for(int s = 0; s < G.V(); s++){
            if(!this.marked[s]){
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v){
        this.marked[v] = true;
        this.id[v] = count;
        for(int w: G.adj(v)){
            if(!this.marked[w]){
                this.dfs(G, w);
            }
        }
    }

    public boolean areConnected(int v, int w){
        return this.id[v] == this.id[w];
    }

    public int count(){
        return count;
    }

    public int id(int v){
        return this.id[v];
    }
}