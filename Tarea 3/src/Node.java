public class Node implements Comparable<Node>{

    public Node left = null;
    public Node right = null;
    public Integer value = 0;
    public Character symbol = 0;

    public Node(){
    }

    public Node(Character symbol, int value){
        this.symbol = symbol;
        this.value = value;
    }

    public Node(Character symbol){
        this.symbol = symbol;
    }

    public Node(int value){
        this.value = value;
    }

    @Override
    public int compareTo(Node o){
        if(this.value != o.value)
            return this.value - o.value;

        return this.symbol < o.symbol? -1: this.symbol > o.symbol? 1: 0;
    }

    @Override
    public String toString(){
        String c = this.symbol != 0? this.symbol + "": "''"; 
        String n = "(" + this.value +")";
        return " "+ c + n +" ";
    }
    
}
