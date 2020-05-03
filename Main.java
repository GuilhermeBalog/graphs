import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        testCC();
    }

    public static void testSearch(){
        Graph myGraph = Graphs.makeGraphFromFile("./tinyG.txt");

        DepthFirstSearch search = new DepthFirstSearch(myGraph, 5);
        System.out.println(myGraph);

        for(int v = 0; v < myGraph.V(); v++){
            if(search.marked(v)){
                System.out.print(v + " ");
            }
        }
        System.out.println();

        if(search.count() != myGraph.V()){
            System.out.print("NOT ");
        }
        System.out.println("connected");
    }

    public static void testPaths(){
        Graph G = Graphs.makeGraphFromFile("./tinyG.txt");
        int s = 9;

        DepthFirstPaths searchFromS = new DepthFirstPaths(G, s);

        for(int v = 0; v < G.V(); v++){
            System.out.print(s + " to " + v + ": ");

            if(searchFromS.hasPathTo(v)){
                for(int x: searchFromS.pathTo(v)){
                    // System.out.print(x);
                    if(x == s){
                        System.out.print(x);

                    } else {
                        System.out.print("-" + x);
                    }
                }
            } else {
                System.out.print("no path found!");
            }

            System.out.println();
        }
    }

    public static void testCC(){
        Graph myGraph = Graphs.makeGraphFromFile("out.txt");
        ConnectedComponents cc = new ConnectedComponents(myGraph);

        int numberOfComponents = cc.count();
        System.out.println(numberOfComponents + " components");

        List<Integer>[] components = (LinkedList<Integer>[]) new LinkedList[numberOfComponents];
        for(int i = 0; i < numberOfComponents; i++){
            components[i] = new LinkedList<Integer>();
        }
        for(int v = 0; v < myGraph.V(); v++){
            components[cc.id(v)].add(v);
        }
        for(int i = 0; i < numberOfComponents; i++){
            System.out.print("component " + i + ": ");
            for(int v: components[i]){
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    public static void testEp(){
        System.out.println();
    }

    public static void minusOne(){
        File file = new File("./OD_graph.txt");

        try(Scanner sc = new Scanner(file)){
            System.out.println(sc.next());
            int e = sc.nextInt();
            System.out.println(e);
            for(int i = 0; i < e; i++){
                int v = sc.nextInt();
                int w = sc.nextInt();
                System.out.println((v-1) + " " + (w-1));
            }

        } catch(IOException error){
            error.printStackTrace();
        }
    }
}