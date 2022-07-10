import java.util.*;

class Solution {
    boolean isPrimeNum(int n) {
        if(n < 2) return false;

        for(int i = 2; i <= (int)Math.sqrt(n); i++)
            if(n % i == 0) return false;

        return true;
    }

    public int solution(int n, int k) {
        int answer = 0;
        Stack<Integer> st = new Stack<Integer>();
        String knum = "";
        String[] dnums;

        while(n != 0) {
            st.push(n % k);
            n = n / k;
        }

        while(!st.empty()) 
            knum = knum + st.pop();
        // System.out.println(knum);
        dnums = knum.split("0");

        for(String d : dnums) {
            // System.out.println("d = " + d);
            if(!d.equals("") && isPrimeNum(Integer.parseInt(d)))
                answer += 1;
        }
        // System.out.println();

        return answer;
    }
}

public class K_LEVEL_2_19 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n1 = 437674;
        int k1 = 3;
        int res1 = 3;

        System.out.println("test case 1");
        if(s.solution(n1, k1) == res1)
            System.out.println("test case 1 ok!");
        else
            System.out.println("test case 1 fail!");

        int n2 = 110011;
        int k2 = 10;
        int res2 = 2;

        System.out.println("test case 2");
        if(s.solution(n2, k2) == res2)
            System.out.println("test case 2 ok!"); 
        else
            System.out.println("test case 1 fail!");
    }
}