import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VorazDinamica {


    // FUNCIONES

    public static List<Activity>  actividades(Activity[] tasks){
        
        int last = 0;
        int min = -1;          
        int s = -1;
        int f = 25; 
        List<Activity> result = new ArrayList<Activity>();   

        while (true) {
            for (int i = last; i < tasks.length; i++) {
                if(tasks[i].f < f && tasks[i].s > s){
                    min = i;
                    f = tasks[i].f;
                }
            } 

            if (last == min) break;
            f = 25;
            s = tasks[min].f;
            result.add(tasks[min]);
            last = min;
        }

        return result;
    }


    public static int[] monedas(int n, int[] d){
        int[][] table = new int[n+1][d.length + 1];

        for (int i = 1; i < n+1; i++) {
            int best = 0;
            int min = i+1;
            for (int j = 0; j < d.length; j++) {
                int coin = d[j];
                if(coin > i) break;
                if(table[i-coin][d.length] < min){
                    min = table[i-coin][d.length];
                    best = i-coin;
                }
            }

            for (int j = 0; j < d.length+1; j++) {
                table[i][j] = table[best][j];
                if(j == d.length || d[j] == i-best)
                    table[i][j]++; 
            }
        }
        return table[n];
    }


    public static int[] mochila(int kg, Product[] pts){
        int[][] table = new int[kg+1][pts.length + 1];

        for (int i = 1; i < kg+1; i++) {
            int best = 0;
            int max = -1;
            int product = 0;
            for(int j = 0; j < pts.length; j++){
                Product item = pts[j];
                if(item.weight > i) break;
                if(table[i-item.weight][pts.length] + item.value > max){
                    max = table[i-item.weight][pts.length] + item.value;
                    best = i-item.weight;
                    product = j;
                }
            }

            for (int j = 0; j < pts.length+1; j++) {
                table[i][j] = table[best][j];
                if(j == pts.length)
                    table[i][j] += pts[product].value;
                else if(pts[j].weight == i-best)
                    table[i][j]++;
            }

        }


        
        return table[kg];
    }



    // PRUEBAS

    public static void test1(){

        Activity[] tasks = {
            new Activity(0,6),
            new Activity(1,4),
            new Activity(2,14),
            new Activity(3,5),
            new Activity(3,9),
            new Activity(5,7),
            new Activity(5,9),
            new Activity(6,10),
            new Activity(8,11),
            new Activity(8,12),
            new Activity(12,16)
        };

        for (Activity a : tasks) {
            System.out.println("De: " + a.s + ", hasta: " + a.f);
        }

        System.out.println();

        List<Activity> result = actividades(tasks);

        for (Activity i : result) {
            System.out.println("Inicio: " + i.s + ", Fin: " + i.f);
        }
    }

    public static void test2(){

        int[] coins = {1, 4, 6};
        int pay = 9;
        int[] result = monedas(9, coins);

        System.out.println("Denominaciones: " + Arrays.toString(coins));
        System.out.println("Cambio: " + pay + "\n");

        for (int i = 0; i < coins.length; i++) 
            if(result[i] != 0)
                System.out.println(result[i] + " monedas de " + coins[i]);

        System.out.println("Total: " + result[coins.length] + " monedas");
        
    }

    public static void test3(){

        Product[] productos = {
            new Product("p1",1,1),
            new Product("p2",2, 6),
            new Product("p3",5,18),
            new Product("p4",6,22),
            new Product("p5",7,28)
        };

        int cap = 11;

        for (Product p : productos) {
            System.out.println("- " + p.name + ": (w: " + p.weight + "kg, v: $" + p.value + ")");
        }
        System.out.println("Capacidad: " + cap + "\n");

        int[] result = mochila(cap, productos);
        
        

        for (int i = 0; i < productos.length; i++) 
            if(result[i] != 0)
                System.out.println(result[i] + " productos de " + productos[i].name);

        System.out.println("Total: $" + result[productos.length] + "\n");

    }


    public static void main(String[] args) {
        test1();
        System.out.println();
        test2();
        System.out.println();
        test3();
    }
    
}
