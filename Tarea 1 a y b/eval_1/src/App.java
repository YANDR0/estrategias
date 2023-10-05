
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class App {

    static int c2, c3;

    // PARTE 1
    public static boolean parte_1_1(int[] array, int num){

        for (int i = 0; i < array.length - 1; i++) 
            for (int j = i+1; j < array.length; j++) 
                if(array[i] + array[j] == num) return true;

        return false;
    }

    public static boolean parte_1_2(int[] array, int num){

        int l = 0, r = array.length - 1; 
        while(l != r){
            if(array[l] + array[r] == num) return true;
            else if(array[l] + array[r] < num) l++;
            else r--;
        }
        return false;
    }

    public static int[][] parte_1_4(int num){

        int[][] matrix = new int[num][num]; 
        for (int i = 0; i < num; i++) {
            matrix[i][i] = 1;
            matrix[i][num-1-i] = 1;
        }
        return matrix;
    }


    //PARTE 2
    public static int mode(int[] array){
        int maxCount = 0, mode = -1;
        for (int i = 0; i< array.length; i++) {
            int count = 0;
            for (int j = 0; j < array.length; j++) {
                c3++;
                if(array[i] == array[j]) count++;   //Comparaciones entre datos C3
            }
            if(count > maxCount){
                maxCount = count;
                c2++;
                mode = array[i];    //Movimiento entre datos C2
            }
        }
        return mode;
    }

    public static void parte_2_1(){
        System.out.println("n\tig-c2\tig-c3\tdi-c2\tdi-c3\tr-c2\tr-c3");
         
        for (int i = 1000; i <= 5000; i+=100) {
            int[] array = new int[i];
            String row = ""+i;
            c2 = c3 = 0;

            //Iguales
            Arrays.fill(array, 1);
            mode(array);
            row += "\t"+c2+"\t"+c3;
            c2 = c3 = 0;

            //Distintos
            Arrays.setAll(array, p -> p+1);
            mode(array);
            row += "\t"+c2+"\t"+c3;
            c2 = c3 = 0;

            for (int j = 0; j < 10; j++) {
                Arrays.setAll(array, p -> (int)Math.round(Math.random()*100));
                mode(array);
            }
            row += "\t"+c2/10.0+"\t"+c3/10;
            System.out.println(row);
        }
    }

    public static void selection(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            int min = i;
            for (int j = i+1; j < array.length; j++){
                c3++;
                min = array[min] > array[j]? j: min;  
            }
            
            if(min != i){
                c2 += 3;
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }
    }

    public static void parte_2_2(){
        System.out.println("n\tor-c2\tor-c3\tco-c2\tco-c3\tr-c2\tr-c3");
         
        for (int i = 1000; i <= 5000; i+=100) {
            int[] array = new int[i];
            String row = ""+i;
            c2 = c3 = 0;

            //Ordenado
            Arrays.setAll(array, p -> p == 0? (int)Math.round(Math.random()*25)+1: p/array[0]);
            array[0] = 0;
            selection(array);
            row += "\t"+c2+"\t"+c3;
            c2 = c3 = 0;

            //Casi ordenado
            Arrays.setAll(array, p -> p == 0? (int)Math.round(Math.random()*25)+1: p/array[0]);
            array[0] = array.length;
            selection(array);
            row += "\t"+c2+"\t"+c3;
            c2 = c3 = 0;

            for (int j = 0; j < 10; j++) {
                Arrays.setAll(array, p -> (int)Math.round(Math.random()*100));
                selection(array);
            }
            row += "\t"+c2/10.0+"\t"+c3/10;
            System.out.println(row);
        }
    }


    //PARTE 3
    public static void bubble(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            boolean changes = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                c3++;
                if(array[j] > array[j+1]){
                    changes = true;
                    c2+=3;
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
            if(!changes) return;
        }
    }

    public static void parte_3(){
        System.out.println("n\tb-c2\tb-c3\tw-c2\tw-c3\tr-c2\tr-c3");
         
        for (int i = 1000; i <= 5000; i+=100) {
            int[] array = new int[i];
            String row = ""+i;
            c2 = c3 = 0;

            //Ordenado
            Arrays.setAll(array, p -> p+1);
            bubble(array);
            row += "\t"+c2+"\t"+c3;
            c2 = c3 = 0;

            //Al revés
            Arrays.setAll(array, p -> array.length - p);
            array[0] = array.length;
            bubble(array);
            row += "\t"+c2+"\t"+c3;
            c2 = c3 = 0;

            for (int j = 0; j < 10; j++) {
                Arrays.setAll(array, p -> (int)Math.round(Math.random()*100));
                bubble(array);
            }
            row += "\t"+c2/10.0+"\t"+c3/10;
            System.out.println(row);
        }
    }


    //PARTE 4
   
    public static boolean oldIsPalindrome(List<Character> ls) {
        int i, j;
        for(i = 0, j = ls.size() - 1; i < j; i++, j--)
            if(ls.get(i) != ls.get(j))
                return false;
        return true;
    }

    public static ArrayList<Character> createArrayListString(String s){
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++) 
            list.add(s.charAt(i));
        return list;
    }

    public static LinkedList<Character> createLinkedListString(String s){
        LinkedList<Character> list = new LinkedList<Character>();
        for (int i = 0; i < s.length(); i++) 
            list.add(s.charAt(i));
        return list;
    }

    public static List<Character> createPalindromeListString(int n, boolean type){

        List<Character> l = type ? new ArrayList<Character>(): new LinkedList<Character>();      
        int ran = (int)Math.round(Math.random()*25)+1;
        for (int i = 0; i < n; i++) 
            l.add(i < (n+1)/2? (char)((i/ran)%26+'a') : (char)(((n-i-1)/ran)%26+'a'));

        return l;
    }

    public static void parte_4_3(){
        System.out.println("n\tArray\tLinked");

        for(int i = 10000; i <= 50000; i+= 1000){
            List<Character> pal;
            String row = ""+i;
            long timer = 0;

            //Array
            pal = createPalindromeListString(i, true);
            timer = System.currentTimeMillis();
            isPalindrome(pal);
            row += "\t" + (System.currentTimeMillis() - timer);  

            //Linked
            pal = createPalindromeListString(i, false);
            timer = System.currentTimeMillis();
            isPalindrome(pal);
            row += "\t" + (System.currentTimeMillis() - timer); 
            
            System.out.println(row);
        }
    }

    public static boolean isPalindrome(List<Character> ls) {
        int i, j;
        ListIterator<Character> first = ls.listIterator(), last = ls.listIterator(ls.size());
        for(i = 0, j = ls.size() - 1; i < j; i++, j--)
            if(first.next() != last.previous())
                return false;
        return true;
    }

    public static void parte_4_7(){

        System.out.println("n\tArray\tLinked");

        for(int i = 1000000; i <= 5000000; i+= 100000){
            List<Character> pal;
            String row = ""+i;
            long timer = 0;

            //Array
            pal = createPalindromeListString(i, true);
            timer = System.currentTimeMillis();
            isPalindrome(pal);
            row += "\t" + (System.currentTimeMillis() - timer);  

            //Linked
            pal = createPalindromeListString(i, false);
            timer = System.currentTimeMillis();
            isPalindrome(pal);
            row += "\t" + (System.currentTimeMillis() - timer); 
            
            System.out.println(row);
        }
    }


       //MAIN PARA IR PROBANDO 
    public static void main(String[] args) throws Exception {

        parte_4_7();

    }

}

