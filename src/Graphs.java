import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Graphs {
    /**
     * Compute the degree of v. Algothms, 4th Edition, pg. 523
     * @param G graph that includes v
     * @param v vertex of G
     * @return the degree of v
     */
    public static int degree(Graph G, int v){
        int degree = 0;
        for(int w: G.adj(v)) degree++;

        return degree;
    }

    /** 
     * Compute the maximum degree of G. Algothms, 4th Edition, pg. 523
    */
    public static int maxDegree(Graph G){
        int max = 0;

        for(int v = 0; v < G.V(); v++)
            if(degree(G, v) > max)
                max = degree(G, v);

        return max;
    }

    /** 
     * Compute the avarage degree of G. Algothms, 4th Edition, pg. 523
    */
    public static int avgDegree(Graph G){
        return 2 * G.E() / G.V();
    }

    /** 
     * count self-loops of G. Algothms, 4th Edition, pg. 523
    */
    public static int numberOfSelfLoops(Graph G){
        int count = 0;
        
        for(int v = 0; v < G.V(); v++)
            for(int w: G.adj(v))
                if(v == w) count++;

        return count / 2;
    }

    public static Graph makeGraphFromFile(String filepath) throws IOException{
        File file = new File(filepath);

        Scanner sc = new Scanner(file);
        return new Balograph(sc);
    }
}