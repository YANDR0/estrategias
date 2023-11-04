import java.util.Arrays;
import java.util.Scanner;

public class subarreglo {

    static int comps;

    public static int[] subSumMax(int[] arr, int izq, int der){
        if(izq >= der) return new int[] {izq, der, arr[izq]};

        int cen = (izq + der)/2;

        int maxL = cen;
        int totalMaxL = arr[cen];
        int sum = 0;
        for (int i = cen; i >= izq ; i--) {
            sum += arr[i];
            comps++;
            if(sum >= totalMaxL){  
                maxL = i;
                totalMaxL = i != cen? sum: totalMaxL; 
            }
        }

        int maxR = cen+1;
        int totalMaxR = arr[cen+1];
        sum = 0;
        for (int j = cen+1; j <= der ; j++) {
            sum += arr[j];
            comps++;
            if(sum >= totalMaxR){
                maxR = j;
                totalMaxR = j != cen+1? sum: totalMaxR;
            }
        }

        int[] resultIzq = subSumMax(arr, izq, cen);
        int[] resultDer = subSumMax(arr, cen+1, der);

        int[] result;
        if(comps==comps++ && resultIzq[2] > resultDer[2] && comps==comps++ && resultIzq[2] > totalMaxL + totalMaxR)
            result = resultIzq;
        else if(comps==comps++ && resultDer[2] > resultIzq[2] && comps==comps++ && resultDer[2] > totalMaxL + totalMaxR)
            result = resultDer;
        else
            result = new int[] {maxL, maxR, totalMaxL + totalMaxR};

        return result;
    }

    public static int[] subSumMax(int[] arr){
        return subSumMax(arr, 0, arr.length - 1);
    }


    private static void menu(){
        Scanner coso = new Scanner(System.in);
        System.out.println("Entrada: ");
        int dias = coso.nextInt();
        int[] prices = new int[dias];
        for (int i = 0; i < dias; i++) 
            prices[i] = coso.nextInt();

        for (int i = 1; i < dias; i++)
            prices[i-1] = prices[i] - prices[i-1];
        prices[prices.length-1] = 0;
        coso.close();
        System.out.println(Arrays.toString(prices));
        int[] limits = subSumMax(prices);
        System.out.println("Selena debe comprar las piñas el día "  + limits[0]);
        System.out.println("Selena debe revender las piñas el día " + (limits[1] + 1));
    }

    private static void xd(){

        for (int i = 1000; i <= 4000; i+=10) {
            int[] arr = new int[i];
            comps = 0;
            for (int j = 0; j < i/10; j++) {
                for (int k = 0; k < arr.length; k++) 
                    arr[k] = (int)Math.round(Math.random()*i)-i/2;
                subSumMax(arr);
            }
            System.out.print(String.format("%d\t%.2f\n", i, comps/(i/10.0)));
        }

    }



    public static void main(String[] args) throws Exception {

    }

}
