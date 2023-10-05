
import java.util.Arrays;

class Heapsort{

    static int comp;
    static int movs;

    private static <T extends Comparable<? super T>> boolean greaterThan(T a, T b) {
        comp++;
        return a.compareTo(b) > 0;
    }

    private static int left(int k){
        return 2*k + 1;
    }

    private static int right(int k){
        return 2*k + 2;
    }

    private static <T> void swap(T a[], int p1, int p2){
        T tmp = a[p1];
        a[p1] = a[p2];
        a[p2] = tmp;
        movs += 3;  //Tres movimientos
    }

    private static <T extends Comparable<? super T>> void maxHeapify(T a[], int root, int heapSize){
        int l = left(root);
        int r = right(root);
        int max = root;
        max = l < heapSize && greaterThan(a[l], a[max])? l: max;
        max = r < heapSize && greaterThan(a[r], a[max])? r: max;
        if(root != max){
            swap(a, root, max);
            maxHeapify(a, max, heapSize);
        }
    }

    public static <T extends Comparable<? super T>> void heapsort(T a[]){
        
        for (int i = a.length/2 - 1; i >= 0 ; i--) 
            maxHeapify(a, i, a.length);
        
        for (int i = a.length - 1; i >= 0 ; i--) {
            swap(a, 0, i);
            maxHeapify(a, 0, i);
        }
    }


    public static void main(String[] args) throws Exception {
        //test();
        posteriori();
    }

    public static void test(){
        Borrego[] arr_1 = {
            new Borrego(85, "Oscar"), new Borrego(92, "Shawn"),
            new Borrego(64, "Cerbero"), new Borrego(73, "Maria"),
            new Borrego(102, "Cordero"), new Borrego(85, "Aguinalda"),
            new Borrego(113, "Lanarica"),new Borrego(102, "WhoolRich"),
            new Borrego(137, "Borreo num.45"), new Borrego(85, "No un lobo"),
        };    

        Borrego[] arr_2 = {
            new Borrego(91, "Juan"), new Borrego(67, "Shawn"),
            new Borrego(91, "Maria"), new Borrego(131, "Maria"),
            new Borrego(37, "Jose"), new Borrego(85, "Aguinalda"),
            new Borrego(129, "Fernando"),new Borrego(77, "Fer"),
            new Borrego(90, "Josefo"), new Borrego(85, "John"),
        };  

        Borrego[] arr_3 = {
            new Borrego(85, "Nombre_1"), new Borrego(92, "Name_1"),
            new Borrego(64, "Nombre_2"), new Borrego(73, "Name_2"),
            new Borrego(102, "Nombre_3"), new Borrego(85, "Name_3"),
            new Borrego(113, "Nombre_4"),new Borrego(102, "Name_4"),
            new Borrego(137, "Nombre_5"), new Borrego(85, "Name_5"),
        };  

        heapsort(arr_1);
        heapsort(arr_2);
        heapsort(arr_3);

        for (int i = 0; i < 10; i++) 
            System.out.println(arr_1[i].toString());
        System.out.println("----------------------------");
        for (int i = 0; i < 10; i++) 
            System.out.println(arr_2[i].toString());
        System.out.println("----------------------------");
        for (int i = 0; i < 10; i++) 
            System.out.println(arr_3[i].toString());
        
    }

    public static void posteriori(){
        for (int i = 1000; i <= 5000; i+= 10) {
            Borrego[] arr = new Borrego[i];
            movs = comp = 0;
            for (int j = 0; j < i/10; j++) {
                Arrays.setAll(arr, p -> new Borrego((int)Math.round(Math.random()*101), "Borrego"));
                heapsort(arr);
            }
            System.out.print(String.format("%d\t%.1f\t%d\t%.1f\n", i, movs/(i/10.0), i, comp/(i/10.0)));
        }  
    }


}

