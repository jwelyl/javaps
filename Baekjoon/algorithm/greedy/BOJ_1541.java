import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1541 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static String input = "";
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		String[] operands;
		int[] nums;
		char[] operators;
		int idx = 0;
		
		input = bf.readLine();
		
		operands = input.split("-");
		nums = new int[operands.length];
		
		for(int i = 0; i < operands.length; i++) {
			String[] oprs = operands[i].split("\\+");
			
			for(String opr : oprs)
				nums[i] += Integer.parseInt(opr);
		}
		
		ans = nums[0];
		for(int i = 1; i < nums.length; i++)
			ans -= nums[i];
		
		System.out.println(ans);
	}
}
