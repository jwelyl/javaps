import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BitMaskingExercise {
    public static void main(String[] args) {
        setup();
        // sumFirstVisitByArray();
        // sumFirstVisitByBit();

       //nums = Arrays.copyOf(nums, 4);
        //cnt = 0;
        // permutationByArray(0, new int [3], new boolean[nums.length]);
        //cnt = 0;
        // permutationByBit(0, new int[3], 0);

        // makePowerSet(0, new boolean[nums.length]);
        //makePowerSet();
    }

    private static int[] nums;

    // 0~31까지의 정수 100개를 무작위로 만들어본다.
    private static void setup() {
        nums = new int[100];
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(32);
        }
        System.out.println("배열: " + Arrays.toString(nums));
    }

    private static void sumFirstVisitByArray() {
        // TODO:nums에서 처음 나온 배열 요소들만 더해서 그 값을 출력하시오.
        // END:
    }

    private static void sumFirstVisitByBit() {
        // TODO: 위 메서드를 bit 연산자로 처리하시오.
        // END:
    }

    static int cnt = 0;

    // TODO: 배열 기반으로 순열을 작성해보고 visited를 왜 원상복구시키고 있는지 고민해보자.
    // END:

    // TODO: bit 연산을 이용해서 순열을 작성해보자.
    // END:

    // TODO: 중복순열을 이용해서 nums의 부분집합을 만들어보자.
    // END:

    // TODO: bitmasking을 이용해서 부분집합을 구해보자.
    // END:

}
