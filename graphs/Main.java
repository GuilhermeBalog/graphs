package graphs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException{
        sp();
    }

    public static void sp(){
        Scanner sc = null;
        
        try{
            File file = new File("res/tinyEWD.txt");
            sc = new Scanner(file);
            sc.useLocale(Locale.ENGLISH);

            EdgeWeightedDigraph G = new EdgeWeightedDigraph(sc);
            int s = 0;
            ShortestPaths sp = new DijkstraSP(G, s);

            for(int t = 0; t < G.V(); t++){
                System.out.print(s + " to " + t);
                System.out.printf(" (%4.2f): ", sp.distTo(t));
                if(sp.hasPathTo(t))
                    for(DirectedEdge e: sp.pathTo(t))
                        System.out.print(e + " ");
                System.out.println();
            }

        } catch(IOException e){
            System.out.println("Não foi possível localizar o arquivo");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    public static void mst(){
        Scanner sc = null;
        
        try{
            File file = new File("res/tinyEWG.txt");
            sc = new Scanner(file);
            sc.useLocale(Locale.ENGLISH);

            EdgeWeightedGraph ewg = new EdgeWeightedGraph(sc);

            System.out.println("\nLazy Prim MST:");
            IMST mst = new LazyPrimMST(ewg);
            for(Edge e : mst.edges())
                System.out.println(e);

            System.out.println("Peso: " + mst.weight());
            System.out.println();

            System.out.println("Eager Prim MST:");
            mst = new PrimMST(ewg);
            for(Edge e : mst.edges())
                System.out.println(e);
                
            System.out.println("Peso: " + mst.weight());
            System.out.println();

            System.out.println("Kruskal MST");
            mst = new KruskalMST(ewg);
            for(Edge e : mst.edges())
                System.out.println(e);
                
            System.out.println("Peso: " + mst.weight());
            System.out.println();

        } catch(IOException e){
            System.out.println("Não foi possível localizar o arquivo");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    public static void dirDfs(String[] args){
        Scanner sc = null;

        try{
            File tinyDG = new File("res/tinyDG.txt");
            sc = new Scanner(tinyDG);

            Graph myGraph = new MyDigraph(sc);
            LinkedList<Integer> sources = new LinkedList<>();

            for(int i = 0; i < args.length; i++){
                sources.add(Integer.parseInt(args[i]));
            }

            DepthFirstSearch reachable = new DepthFirstSearch(myGraph, sources);

            for(int v = 0; v < myGraph.V(); v++){
                if(reachable.marked(v)){
                    System.out.print(v + " ");
                }
            }
            System.out.println();

        } catch(IOException e){
            System.out.println("Não foi possível localizar o arquivo");
            e.printStackTrace();
        } finally {
            sc.close();
        }
        
    }

    public static void testSearch() throws IOException{
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

    public static void testPaths() throws IOException{
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

    public static void testEp() throws IOException{
        Graph myGraph = Graphs.makeGraphFromFile("./cenario3.txt");
        ConnectedComponents cc = new ConnectedComponents(myGraph);

        int numberOfComponents = cc.count();

        List<Integer>[] components = (LinkedList<Integer>[]) new LinkedList[numberOfComponents];
        for(int i = 0; i < numberOfComponents; i++){
            components[i] = new LinkedList<Integer>();
        }
        for(int v = 0; v < myGraph.V(); v++){
            components[cc.id(v)].add(v);
            // [0] -> 1, 2, 3
            // [1] -> 4, 5, 6, 7
        }

        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < numberOfComponents; i++){
            int size = components[i].size();

            if(map.containsKey(size)){
                int currentValue = map.get(size);
                map.put(size, ++currentValue);
            } else {
                map.put(size, 1);
            }
        }

        System.out.println("Resultado:");
        System.out.println(numberOfComponents + " componentes:\n");

        for(int size: map.keySet()){
            int n = map.get(size);
            System.out.println(n + " componentes com " + size + " vertices");
        }
    }

    public static void testCC() throws IOException{
        Graph myGraph = Graphs.makeGraphFromFile("./tinyG.txt");
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
            System.out.print("component " + i + ": " + "(" + components[i].size() + " vertices): ");
            for(int v: components[i]){
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    public static void testMap(){
        Map<Integer, Integer> map = new HashMap<>();
        // map.put(0, 2);
        System.out.println(map.get(0));
        map.put(0, 1);
        System.out.println(map.get(0));
    }

    public static void minusOne(){
        File file = new File("./cenario2.txt");

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