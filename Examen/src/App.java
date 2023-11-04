
public class App {


    public static boolean isMinHeap(int[] arr, int root){

        if(root >= arr.length) return true;

        int left = root*2+1;
        int right = root*2+2;

        if(left < arr.length && arr[root] > arr[left]) return false;
        if(right < arr.length && arr[root] > arr[right]) return false;

        return isMinHeap(arr, left) && isMinHeap(arr, right);
    }

    public static boolean isMinHeap_2(int[] arr){

        for(int i = 0; i < arr.length/2; i++){
            int left = i*2+1;
            int right = i*2+2;
            if(left < arr.length && arr[i] > arr[left]) return false;
            if(right < arr.length && arr[i] > arr[right]) return false;
        }
        return true;
    }


    public static short countingSort(short[] arr){

        int[] conteo = new int[Short.MAX_VALUE - Short.MIN_VALUE];  //

        for(short i: arr)
            conteo[i+Short.MAX_VALUE]++;
        

        int max = 0;

        for (int i = 0; i < conteo.length; i++) 
            if(conteo[max] < conteo[i]) max = i;

        
        return (short)(max + Short.MIN_VALUE+1);
    }


    public static void main(String[] args) throws Exception {

        short[] min = {-5,-5,-5,-5,-5,54,28,-1,2,-3,-4,-5,41,7,2};

        System.out.println(countingSort(min));
    }
}
