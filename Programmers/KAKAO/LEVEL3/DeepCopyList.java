import java.util.*;

public class DeepCopyList {
    static List<Integer> l1 = new ArrayList<Integer>();
    static List<Integer> l2 = new ArrayList<Integer>();
    static List<Integer> l3 = new ArrayList<Integer>();

    static void printList(List<Integer> l) {
        for(int i = 0; i < l.size(); i++) 
            System.out.print(l.get(i) + " ");
        System.out.println();
    }

    public static void main(String[] args) {    
        for(int i = 0; i < 10; i++)
            l1.add(i);
        l2 = l1;    //  shallow copy

        System.out.println("Shallow copy");
        printList(l1);
        printList(l2);
    
        for(int i = 10; i < 12; i++)
            l2.add(i);

        System.out.println("Shallow copy modification");
        printList(l1);
        printList(l2);
      
        System.out.println("Deep copy");
        l3.addAll(l1);

        printList(l1);
        printList(l3);

        for(int i = 12; i < 16; i++)
            l3.add(i);

        System.out.println("Deep copy modification");
        printList(l1);
        printList(l3);
    }
}