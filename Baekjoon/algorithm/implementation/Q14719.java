import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int H, W;
	static int ans = 0;
	static int[][] field;
	static final int WALL = 1, EMPTY = 0;
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(bf.readLine());
		H = Integer.parseInt(tokens.nextToken());
		W = Integer.parseInt(tokens.nextToken());
		
		field = new int[H][W];
		
		tokens = new StringTokenizer(bf.readLine());
		for(int i = 0; i < W; i++) {
			int height = Integer.parseInt(tokens.nextToken());
			
			for(int j = 0; j < height; j++) {
				field[H - 1 - j][i] = 1;
			}
		}
		
//		for(int i = 0; i < H; i++) {
//			for(int j = 0; j < W; j++)
//				System.out.print(field[i][j]);
//			System.out.println();
//		}
		
		for(int i = H - 1; i >= 0; i--) {
			int lw = -1, rw = -1;	//	왼쪽 벽 위치, 오른쪽 벽 위치(없을 경우 -1)
			int waterBlocks = 0;	//	해당 층에 고이는 물의 개수
			
			for(int j = 0; j < W; j++) {
				if(lw == -1 && rw == -1) {	//	벽이 하나도 없는 상태에서
					if(field[i][j] == WALL)	//	처음 벽을 만날 경우
						lw = j;				//	왼쪽 벽 설정
					continue;
				}
				
				if(lw != -1 && rw == -1) {
					if(field[i][j] == WALL)	{	//	왼쪽 벽이 생긴 이후 처음 벽을 만난 경우
						rw = j;	//	해당 벽을 오른쪽 벽으로 설정
						waterBlocks += (rw - lw - 1);	//	왼쪽 벽과 오른쪽 벽 사이의 공간을 물로 채움
						lw = j;		//	오른쪽 벽을 다시 왼쪽 벽으로 간주
						rw = -1;	
					}
					continue;
				}
			}
			
			ans += waterBlocks;
		}

		System.out.println(ans);
	}
}