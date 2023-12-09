package Part2;

import java.io.File;
import java.util.Scanner;

public class Part2 {

    private static void subOcurrences(String origin, String sub){

        int[] ocurrences = new int[sub.length()];

        for (int i = 0; i < origin.length(); i++) {
            char curr = origin.charAt(i);
            for (int j = sub.length() - 1; j >= 0 ; j--) {
                char c = sub.charAt(j);
                if(curr == c)
                    ocurrences[j] += j == 0? 1: ocurrences[j-1];
            }
        }
        System.out.println(ocurrences[ocurrences.length-1]);
    }


    public static void main(String[] args) throws Exception {
        
        File myObj = new File("src/Part2/In.txt");
        Scanner s = new Scanner(myObj);
        //Scanner s = new Scanner(System.in);
        int problems = s.nextInt();
        for (int i = 0; i < problems; i++) 
            subOcurrences(s.next(), s.next());
        s.close();
    }
    
}
