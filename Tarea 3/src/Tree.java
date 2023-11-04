import java.util.ArrayList;
import java.util.List;

public class Tree {

    public static int size;
    public static Node_ root = null;
    private static final Tree tree = new Tree();


    // COSAS DEL ÁRBOL NORMAL
    private Tree(){
        size = 0;
    }

    public static Tree createTree(){
        return tree;
    }

    public static void addNode(Node_ N){
        if(root == null){
            root = N;
            return;
        } 

        Node_ curr = root;

        while (curr != null) {
            if(N.value < curr.value)
                if (curr.left != null) 
                    curr = curr.left;
                else{
                    curr.left = N;
                    return;
                }

            if(N.value > curr.value)
                if (curr.right != null) 
                    curr = curr.right;
                else{
                    curr.right = N;
                    return;
                }
        }
    }
     
    

    // EJERCICIOS
    private static boolean existsPath(Node_ curr, int n, int total){
        if(curr == null)
            return n == total;

        int odd = curr.value%2;
        return existsPath(curr.left, n, total + odd) || existsPath(curr.right, n, total + odd);
    }

    public static boolean existsPath(Node_ curr, int num){
        return existsPath(root, num, 0);
    }


    private static boolean isBST(Node_ curr, int left, int right){
        if(curr == null)
            return true;

        if(curr.value < left || curr.value > right)
            return false;

        return isBST(curr.left, left, curr.value) && isBST(curr.right, curr.value, right);
    }

    public static boolean isBST(Node_ curr){
        return isBST(curr, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    public static List<List<Node_>> getNodeListPerLevel(Node_ r){
        List<List<Node_>> results = new ArrayList<List<Node_>>();
        List<Node_> parents = new ArrayList<Node_>();
        
        parents.add(r);

        while(!parents.isEmpty()){
            List<Node_> childs = new ArrayList<Node_>();
            
            for(Node_ n: parents){
                if(n.left != null) childs.add(n.left);
                if(n.right != null) childs.add(n.right);
            }

            results.add(parents);
            parents = childs;
        }

        return results;  
    }


    // PRUEBAS
    public static void arbol1() {

        Node_[] t1 = new Node_[7];
        t1[0] = new Node_(5);
        t1[1] = new Node_(3);
        t1[2] = new Node_(7);
        t1[3] = new Node_(2);
        t1[4] = new Node_(4);
        t1[5] = new Node_(6);
        t1[6] = new Node_(9);

        for (Node_ n : t1) 
            addNode(n);
        

        System.out.println(existsPath(root, 1));
        System.out.println(existsPath(root, 2));
        System.out.println(existsPath(root, 3));
        System.out.println(existsPath(root, 4));
    }

    public static void arbol2() {

        Node_[] t1 = new Node_[6];
        t1[0] = new Node_(5);
        t1[1] = new Node_(2);
        t1[2] = new Node_(8);
        t1[3] = new Node_(4);
        t1[4] = new Node_(7);
        t1[5] = new Node_(9);

        for (Node_ n : t1) 
            addNode(n);

        System.out.println(isBST(root));

        Node_ n1 = new Node_(3);
        Node_ n2 = new Node_(1);

        t1[1].right = n2;
        t1[2].left = n1;

        System.out.println(isBST(root));
    }

    public static void arbol3() {

        Node_ t1 = new Node_(5),
        t2 = new Node_(3),
        t3 = new Node_(9),
        t4 = new Node_(4),
        t5 = new Node_(8),
        t6 = new Node_(2),
        t7 = new Node_(6),
        t8 = new Node_(1);

        root = t1;
        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        t3.left = t5;
        t3.right = t6;
        t5.left = t7;
        t5.right = t8;

        List<List<Node_>> a = getNodeListPerLevel(root);
        String xd = "[";
        for(List<Node_> aver: a){
            String s = "[";
            for(Object i: aver){
                Node_ n = (Node_)i;
                s += n.value + "-";
            }
            s += "],";
            xd += s;
        }
        System.out.println(xd + "]");

    }
 

    public static void main(String[] args) throws Exception {
        //arbol1();
        arbol2();
        //arbol3();

    }
}
