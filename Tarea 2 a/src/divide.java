import java.util.Arrays;

public class divide {

    public static int pow(int x, int y){
        return y == 0? 1: y%2 == 0? pow(x*x, y/2): pow(x*x, y/2)*x;
    }



    private static int binarySearch(int[] arr, int key, int izq, int der){
        int cen = izq+(der-izq)/2;
        if(arr[cen] == key) return cen;
        if(izq >= der) return -1;
        return arr[cen] > key? binarySearch(arr, key, izq, cen): binarySearch(arr, key, cen+1, der);
    }

    public static int binarySearch(int[] arr, int key){
        return binarySearch(arr, key, 0, arr.length-1);
    }



    private static int[] mArr;

    private static void merge(int[] arr, int izq, int cen, int der){
        int i = izq;
        int j = cen + 1;
        int k = izq;

        for (int z = izq; z <= der; z++)
            mArr[z] = arr[z];

        while (i <= cen && j <= der)
            arr[k++] = mArr[i] <= mArr[j]? mArr[i++]: mArr[j++];

        while (i <= cen)
            arr[k++] = mArr[i++];
        while (j <= der)
            arr[k++] = mArr[j++];
    }

    private static void mergeSort(int[] arr, int izq, int der){
        if(izq >= der) return;
        int cen = (der+izq)/2;
        mergeSort(arr, izq, cen);
        mergeSort(arr, cen+1, der);
        merge(arr, izq, cen, der);
    }

    public static void mergeSort(int[] arr){
        mArr = new int[arr.length];
        mergeSort(arr, 0, arr.length-1);
    }



    private static void swap(int a[], int p1, int p2){
        int tmp = a[p1];
        a[p1] = a[p2];
        a[p2] = tmp;
    }

    private static int particion(int[] arr,int izq, int der){
        int less = izq;
        int more = izq;
        int piv = arr[der];
        for (int i = izq; i < der; i++) {
            if(arr[more] <= piv) swap(arr, more, less++);
            more++;
        }  
        swap(arr, der, less);
        return less;
    }

    private static void quickSort(int[] arr, int izq, int der){
        if(izq >= der) return;
        int less = particion(arr, izq, der);
          
        quickSort(arr, izq, less-1);
        quickSort(arr, less+1, der);
    }

    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }



    private static int estadistico(int[] arr, int izq, int der, int index){
        int estad = particion(arr, izq, der);
        if(estad == index) return arr[index];
        return estadistico(arr, index < estad? izq: estad+1 , index < estad? estad-1: der, index);
    }

    public static int estadistico(int[] arr, int index){
        if(index < 0 || index > arr.length - 1) return 0; 
        int[] xd = arr.clone();
        return estadistico(xd, 0, arr.length-1, index);
    }

    public static int m(int[] arr){
        return estadistico(arr, arr.length/2);
    }
    public static void main(String[] args) throws Exception {

        int[] aver = { 12, 11, 13, 5, 6, 7, 1, 10};


        //{1}

        System.out.println(Arrays.toString(aver)); 
        System.out.println(estadistico(aver, 0));   
        System.out.println(estadistico(aver, aver.length/2));   
        quickSort(aver);
        System.out.println(Arrays.toString(aver)); 
           
    }
    
}
