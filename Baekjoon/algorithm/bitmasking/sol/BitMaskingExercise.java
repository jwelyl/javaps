import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 좋은아침_연습 {
    static char[] src = { 'a', 'b', 'c', 'd' };

    public static void main(String[] args) {
        // 1. src에서 3개를 뽑아서 만들 수 있는 순열을 모두 출력하시오.
        System.out.println("순열");
        //makePermutation(0, new char[3], new boolean[src.length]);

        // 2. src에서 3개를 뽑아서 만들 수 있는 조합을 모두 출력하시오.
        System.out.println("조합");
        //makeCombination(0,new char[3], 0);

        // 3. src로 구성할 수 있는 모든 부분집합을 출력하시오.
        System.out.println("부분집합");
        powerSetDupPer(0, new boolean[4]);

    }

    /**
     * 기본 순열 - 모든 요소가 중복되지 않고 순서를 고려해서 나온다. ,abc, acb, ....
     * 
     * @param nth
     *            - 몇번째
     * @param choosed
     *            - 선택된 녀석
     * @param visited
     *            - 어떤 요소를 사용한 적이 있는가?
     */
    static void makePermutation(int nth, char[] choosed, boolean[] visited) {
        // 기저조건: 몇 번째꺼를 고르는데choosed를 다 채웠다~~~
        if (nth == choosed.length) {
            System.out.println(Arrays.toString(choosed));
            return;
        }

        // inductive part
        for (int i = 0; i < src.length; i++) {
            if (!visited[i]) {
                // 해당 하는 녀석을 사용하기
                visited[i] = true;
                choosed[nth] = src[i];
                makePermutation(nth + 1, choosed, visited);
                // 해당하는 녀석을 사용 안한척하기
                visited[i] = false;
            }
        }
    }

    // abc aab   --> 중복순열
    static void makePermutationDup(int nth, char[] choosed) {
        // 기저조건: 몇 번째꺼를 고르는데choosed를 다 채웠다~~~
        if (nth == choosed.length) {
            System.out.println(Arrays.toString(choosed));
            return;
        }

        // inductive part
        for (int i = 0; i < src.length; i++) {
            // 해당 하는 녀석을 사용하기
            choosed[nth] = src[i];
            makePermutationDup(nth + 1, choosed);
            // 해당하는 녀석을 사용 안한척하기

        }
    }

    /**
     * 조합: 중복되지 않게 뽑는데 순서가 없다!!!
     * 
     * @param nth
     * @param choosed
     * @param startIdx
     */
    static void makeCombination(int nth, char[] choosed, int startIdx) {
        if (nth == choosed.length) {
            System.out.println(Arrays.toString(choosed));
            return;
        }

        for (int i = startIdx; i < src.length; i++) {
            choosed[nth] = src[i];
            // 중복 방지를 위한 장치: i+1
            makeCombination(nth + 1, choosed, i + 1);
        }
    }

    // 그럼 중복 조합은??
    static void makeCombinationDup(int nth, char[] choosed, int startIdx) {
        if (nth == choosed.length) {
            System.out.println(Arrays.toString(choosed));
            return;
        }

        for (int i = startIdx; i < src.length; i++) {
            choosed[nth] = src[i];
            makeCombination(nth + 1, choosed, i);
        }
    }

    /**
     *
     * @param toCheck
     *            어디까지 포함여부를 체크했냐?
     * @param checked
     *            전체 요소의 체크 상태
     */
    static void powerSetDupPer(int toCheck, boolean[] checked) {
        if (toCheck == checked.length) {
            print(checked);
            return;
        }

        // toCheck에 해당하는 요소를 선택할꺼냐?말꺼냐?
        checked[toCheck] = true;
        powerSetDupPer(toCheck + 1, checked);
        checked[toCheck] = false;
        powerSetDupPer(toCheck + 1, checked);

    }

    static void print(boolean[] temp) {
        //System.out.println(Arrays.toString(temp));
        List<Character> selected = new ArrayList<>();
        List<Character> unselected = new ArrayList<>();
        for(int i=0; i<temp.length; i++) {
            if(temp[i]) {
                selected.add(src[i]);
            }else {
                unselected.add(src[i]);
            }
        }
        System.out.println(selected+" : "+unselected);
    }

}
