import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly
 * @performance KB ms
 * @category #
 * @memo
 * @etc
 * 
 */

public class BOJ_2108 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N;

	static int[] nums;
	static Num[] numbers = new Num[8001];

	static double avg = 0;
	static int mid;
	static int mode;
	static int range;
	static int max = -Integer.MAX_VALUE, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());

		nums = new int[N];
		
		for(int i = 0; i <= 8000; i++) 
			numbers[i] = new Num(i - 4000);

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(bf.readLine());

			if (num > max)
				max = num;
			if (num < min)
				min = num;

			nums[i] = num;
			avg += num;
			
			numbers[num + 4000].cnt++;
		}
		
		Arrays.sort(numbers);
		if(numbers[0].cnt == numbers[1].cnt)
			mode = numbers[1].num;
		else
			mode = numbers[0].num;

		avg /= N;
		Arrays.sort(nums);
		mid = nums[N / 2];
		range = max - min;

		sb.append(Math.round(avg)).append("\n")
			.append(mid).append("\n")
			.append(mode).append("\n")
			.append(range).append("\n");

		System.out.println(sb);
	}

	static class Num implements Comparable<Num> {
		int num; // 수
		int cnt; // 수가 나타난 횟수
		
		Num(int num) {
			this.num = num;
			this.cnt = 0;
		}
		
		@Override
		public int compareTo(Num number) {
			// TODO Auto-generated method stub
			int ret = -Integer.compare(this.cnt, number.cnt);
		
			if(ret == 0) {
				ret = Integer.compare(this.num, number.num);
			}
			
			return ret;
		}
	}
}
