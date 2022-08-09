import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961 {
	static int N;
	static int[] sour, bitter;
	static boolean[] isSelected;
	static int s, b;
	static int minDiff = Integer.MAX_VALUE;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(bf.readLine());
		
		sour = new int[N];
		bitter = new int[N];
		isSelected = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			
			sour[i] = Integer.parseInt(tokens.nextToken());
			bitter[i] = Integer.parseInt(tokens.nextToken());
		}
		
		subset(0, 0);
		
		System.out.println(minDiff);
	}
	
	static void subset(int idx, int selected) {
		if(idx == N) {	//	재료 N개(0~N-1)를 모두 고를지 말지 결정했을 때
			if(selected == 0)	//	1개도 고르지 않은 경우는 제외 
				return;
			
			s = 1;	//	Pi
			b = 0;	//	Sigma
			
			for(int i = 0; i < N; i++) {
				if(isSelected[i]) {	//	선택된 재료
					s *= sour[i];		//	신맛 다 곱함
					b += bitter[i];		//	쓴 맛은 다 더함
				}
			}
			
			if(minDiff > (int)Math.abs(s - b))	//	신맛 쓴맛 차이가 더 적어졌을 경우
				minDiff = (int)Math.abs(s - b);
			
			return;
		}
		
		isSelected[idx] = true;			//	현재 재료 선택 o
		subset(idx + 1, selected + 1);
		
		isSelected[idx] = false;		//	현재 재료 선택 x
		subset(idx + 1, selected);
	}
}
