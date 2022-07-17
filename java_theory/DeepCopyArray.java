import java.util.*;

public class DeepCopyArray {
    static int[] arr1, arr2, arr3;

    static void printArray(int[] arr) {
        for(int i = 0; i < arr.length; i++) 
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        arr1 = new int[10];
        
        for(int i = 0; i < 10; i++)
            arr1[i] = i;
        arr2 = arr1;    //  shallow copy

        System.out.println("Shallow copy");
        printArray(arr1);
        printArray(arr2);
    
        for(int i = 0; i < 10; i++)
            if(i % 2 == 0)
                arr2[i] = 2 * i;

        System.out.println("Shallow copy modification");
        printArray(arr1);
        printArray(arr2);

        System.out.println("Deep copy");
        // arr3 = arr1.clone();

        arr3 = new int[arr1.length];
        System.arraycopy(arr1, 0, arr3, 0, arr1.length);

        printArray(arr1);
        printArray(arr3);

        for(int i = 0; i < 10; i++)
            if(i % 2 == 1)
                arr3[i] = 2 * i;

        System.out.println("Deep copy modification");
        printArray(arr1);
        printArray(arr3);
    }
}