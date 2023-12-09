package Part1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge{
    public String destination;
    public int weight;

    public Edge(String destination, int weight){
        this.destination = destination;
        this.weight = weight;
    }
}

class Node implements Comparable<Node>{
    public Integer cost = null;
    public String parent = null;
    public String value;

    public Node(String value){
        this.value = value;
    }

    public Node(String value, int cost){
        this.cost = cost;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        if(this.cost == null && o.cost == null) return 0;
        if(this.cost == null) return 1;
        if(o.cost == null) return 1;
        return this.cost - o.cost;
    }
}

public class Part1 {

    static HashMap<String, ArrayList<Edge>> Grafo = new HashMap<>();
    static HashMap<String, Node> Nodes = new HashMap<>();
    static PriorityQueue<Node> Qiwi = new PriorityQueue<>();
    static ArrayList<String> Visited = new ArrayList<>();

    private static void createGraf() throws Exception{
        // Read file and get first line
        File myObj = new File("src/Part1/EA4.in");  //Quitar al terminar
        Scanner s = new Scanner(myObj);
        //Scanner s = new Scanner(System.in);
        int numNodes = s.nextInt();
        int numEdges = s.nextInt();

        //Create nodes and edges
        for(int i = 0; i < numNodes; i++){
            String n = s.next();
            Grafo.put( n, new ArrayList<Edge>());
            Nodes.put( n, new Node(n));
        }
        for(int i = 0; i < numEdges; i++){
            String node = s.next();
            Grafo.get(node).add(new Edge(s.next(), s.nextInt()));    
            //Grafo.put(node, tmp);   //Dado que son apuntadores creo que no se necesita esto
        }
        String origin = s.next();
        s.close();

        Node first = Nodes.get(origin);
        first.cost = 0;
        //Nodes.put(origin, first);   //Dado que son apuntadores creo que no se necesita esto
        Qiwi.add(first);
        for (String curr : Nodes.keySet()) {
            if(curr == origin) continue;
            Qiwi.add(Nodes.get(curr));
        }
    }

    private static void Dijkstra(){

        while (!Qiwi.isEmpty()) {
            
            Node u = Qiwi.poll();
            Visited.add(u.value);
            ArrayList<Edge> adjacents = Grafo.get(u.value);

            //Check edges and relax nodes
            for(Edge edge : adjacents) {
                //Check visited list 
                boolean ignore = false;
                for (String n : Visited) {
                    if(n == edge.destination){
                        ignore = true;
                        break;
                    }
                }
                if (ignore) continue;

                //Compare values 
                Node v = Nodes.get(edge.destination);
                if(u.cost != null && (v.cost == null || u.cost + edge.weight < v.cost)){
                    v.cost = u.cost + edge.weight;
                    v.parent = u.value;
                    //Nodes.put(v.value, v);  //Dado que son apuntadores creo que no se necesita esto
                }
            }
        
            //Update kiwi
            int size = Qiwi.size();
            for(int i = 0; i < size; i++)
                Qiwi.add(Qiwi.poll());
        }
    }


    public static void main(String[] args) throws Exception {
        createGraf();
        Dijkstra();
        for (String a: Nodes.keySet()) {
            System.out.println(a + ": " + Nodes.get(a).cost);
        }
    }

}
