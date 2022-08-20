import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class BOJ_1811 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M, B;
	static int[][] originalMap;
	static int maxH = Integer.MIN_VALUE, minH = Integer.MAX_VALUE;

	static int ansTime = Integer.MAX_VALUE; // 높이 통일시키는데 최소 시간
	static int ansHeight; // 통일시킨 높이

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());

		originalMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				originalMap[i][j] = Integer.parseInt(tokens.nextToken());
				if(originalMap[i][j] > maxH)
					maxH = originalMap[i][j];
				if(originalMap[i][j] < minH)
					minH = originalMap[i][j];
			}
		}
		
		for(int h = minH; h <= maxH; h++)
			setHeight(h);
		
		System.out.println(ansTime + " " + ansHeight);
	}

	static void setHeight(int height) {
		int plus = B;	//	퍼내서 다른데에 채워넣을 수 있는 양
		int minus = 0;	//	height로 채워넣기 위해서 부족한 양
		int time = 0;	//	height로 맞추는 데 필요한 시간
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int h = originalMap[i][j];
				
				if(h == height)
					continue;
				else if(h < height)
					minus += (height - h);	//	부족한 만큼 블록을 놓아야 함
				else
					plus += (h - height);	//	더 높은 만큼 블록을 퍼내야 함
			}
		}
		
		if(minus > plus)	//	놓아야 할 블록 양이 퍼낸 블록 양보다 많을 경우 불가함
			time = Integer.MAX_VALUE;
		else {
			time = minus;					//	블록 놓는 시간
			time += (plus - B) * 2; 		//	블록 퍼내는 시간
		}
		
//		System.out.println("Height = " + height + "로 맞추는 시간 = " + time);

		if(ansTime > time) {
			ansTime = time;
			ansHeight = height;
		} else if(ansTime == time) {
			if(ansHeight < height)
				ansHeight = height;
		}
	}
}
