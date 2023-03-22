package homework.sandro.mergeSort;

import java.util.Arrays;
import java.util.Random;

public class MergeSortAlgo {
    public static Random rand = new Random();

    public static void main(String[] args) {
        int[] mergeArray = createArray(20);
        System.out.println("--- kontroll Array ---");
        System.out.println(Arrays.toString(mergeArray));
        System.out.println("---");
        mergeSort(mergeArray,0, mergeArray.length);
        System.out.println(Arrays.toString(mergeArray));

//Beispiel
//        Arrays.sort(mergeArray);
//        System.out.println(Arrays.toString(mergeArray));

    }

    public static void mergeSort(int[] arr, int lo, int hi) {
        if (hi - lo <= 1) return;
        int mid = (lo + hi) / 2;

        mergeSort(arr, lo, mid);
        mergeSort(arr, mid, hi);

        int[] temp = new int[hi - lo];
        int i = lo;
        int j = mid;
        int k = 0;

        while ((i < mid) && (j < hi)) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                ++i;
                ++k;
            } else {
                temp[k] = arr[j];
                j++;
                k++;
            }
        }
        while (i < mid) {
            temp[k] = arr[i];
            ++i;
            ++k;
        }
        while (j < hi) {
            temp[k] = arr[j];
            ++j;
            ++k;
        }
        for (int l = lo; l < hi; l++) {
            arr[l] = temp[l - lo];
        }
        System.out.println(Arrays.toString(arr));
    }


    public static int[] createArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = rand.nextInt(101);
        }
        return result;
    }

}
