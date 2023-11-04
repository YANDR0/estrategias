import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman {

    static HashMap<Character, ArrayList<Boolean>> m = new HashMap<Character, ArrayList<Boolean>>();
    static ArrayList<Boolean> r;


    public static Node treeCreation(char[] s){

        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        for (Character c: s){
            Integer total = count.get(c);
            if(total == null) count.put(c, 1);
            else count.put(c, total+1);
        }

        PriorityQueue<Node> kiwi = new PriorityQueue<Node>();
        for(Character c: count.keySet())
            kiwi.offer(new Node(c, count.get(c)));

        while (kiwi.size() > 1) {
            Node p = kiwi.poll();
            Node q = kiwi.poll();
            Node n = new Node(p.value + q.value);
            n.left = p;
            n.right = q;
            kiwi.offer(n);

            //NO SE NECESITA, SOLO ME DEJA VER EL ÁRBOL
            /*
            System.out.print(p + " --> ");
            System.out.print(n);
            System.out.print(" <-- " + q + "\n");
            */
        }

        return kiwi.poll();
    }


    public static void encoding(Node curr, Boolean bit){
        r.add(bit);
        if(curr.left != null && curr.right != null){
            encoding(curr.left, false);
            encoding(curr.right, true);
        }
        else
            m.put(curr.symbol, (ArrayList<Boolean>)r.clone());
        r.remove(r.size()-1);
    }

    public static void encoding(Node root){
        r = new ArrayList<Boolean>();
        encoding(root.left, false);
        encoding(root.right, true);
    }

 
    public static ArrayList<Boolean> recoding(char[] s){  
        ArrayList<Boolean> sPrime = new ArrayList<Boolean>();
        for(char c: s)
            sPrime.addAll(m.get(c));
        return sPrime;
    }


    public static StringBuilder decoding(ArrayList<Boolean> code, Node root){
        StringBuilder s = new StringBuilder();
        ArrayList<Boolean> sPrime = (ArrayList<Boolean>)code.clone();

        while (sPrime.size() > 0) {
            Node curr = root;
            while (curr.left != null && curr.right != null) {
                curr = sPrime.get(0)? curr.right: curr.left;
                sPrime.remove(0);
            }
            s.append(curr.symbol + "");
        }
        return s;
    }


    public static void huffman(String str){
        char[] array = str.toCharArray();
        Node n = treeCreation(array);                   //Paso 1
        encoding(n);                                    //Paso 2
        ArrayList<Boolean> result = recoding(array);    //Paso 3

        ArrayList<String> string = new ArrayList<String>();
        for(boolean i: result)
            string.add(i? "1": "0");
        System.out.println("\nTexto codificado: ");
        System.out.println(String.join("", string));

        StringBuilder decode = decoding(result, n);
        System.out.println("Texto original:\n" + decode); 
    }


    public static void main(String[] args) {

        String string1 = "ata la jaca a la estaca";

        String string2 = """
        Everyone knows Mario is cool as f--k. But who knows what he's thinking? Who knows why he crushes 
        turtles? And why do we think about him as fondly as we think of the mystical (nonexistent?) 
        Dr Pepper? Perchance.\n I believe it was Kant who said "Experience without theory is blind, but 
        theory without experience is mere intellectual play." Mario exhibits experience by crushing turts 
        all day, but he exhibits theory by stating "Lets-a go!" Keep it up, baby\nWhen Mario leaves his 
        place of safety to stomp a turty, he knows that he may Die. And yet, for a man who can purchase 
        lives with money, a life becomes a mere store of value. A tax that can be paid for, much as a 
        rich man feels any law with a fine is a price. We think of Mario as a hero,but he is simply a one 
        percenter of a more privileged variety. The lifekind. Perchance.\n
        """;

        huffman(string1);
        huffman(string2);
    }
    
}
