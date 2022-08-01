import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][] gears = new char[4][8];
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int K, num, dir; // 회전 횟수, 회전시킬 톱니 번호(0~3), 회전 방향(-1 : 반시계, 0 : 무회전, 1 : 시계방향)
	static int score = 0;

	static void printGears() {
		System.out.println("********************");
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 8; j++)
				System.out.print(gears[i][j]);
			System.out.println();
		}
		System.out.println("********************");
	}
	
	static void calcScore() {
		score = 0;

		if (gears[0][0] == '1')
			score += 1;
		if (gears[1][0] == '1')
			score += 2;
		if (gears[2][0] == '1')
			score += 4;
		if (gears[3][0] == '1')
			score += 8;
	}

	static void rotate(int gear, int dir) {
		if(dir == 0) return;
		
		char[] tmp = new char[8];

		if (dir == -1) {	//	반시계방향 회전일 경우
			for (int i = 1; i < 8; i++)
				tmp[i - 1] = gears[gear][i];
			tmp[7] = gears[gear][0];
		} else if(dir == 1) {	//	시계방향 회전일 경우
			for (int i = 0; i < 7; i++)
				tmp[i + 1] = gears[gear][i];
			tmp[0] = gears[gear][7];
		}
		gears[gear] = tmp;
	}

	//	gear부터 dir 방향으로 회전시켜서 다른 톱니들도 회전시킬 수 있으면 회전
	static void rotateAll(int gear, int dir) {
		int[] rotateDir = new int[4];
		rotateDir[gear] = dir;
		
		int d = dir;
		//	gear 기준 왼쪽 톱니바퀴들 확인
		for(int i = gear - 1; i >= 0; i--) {
			d = -d;
			if(gears[i + 1][6] != gears[i][2])
				rotateDir[i] = d;
			else break;
		}
		
		d = dir;
		//	gear 기준 오른쪽 톱니바퀴들 확인
		for(int i = gear + 1; i < 4; i++) {
			d = -d;
			if(gears[i - 1][2] != gears[i][6])
				rotateDir[i] = d;
			else break;
		}
		
		for(int i = 0; i < 4; i++) {
			rotate(i, rotateDir[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		for (int i = 0; i < 4; i++) {
			String input = bf.readLine();
			for (int j = 0; j < 8; j++) {
				gears[i][j] = input.charAt(j);
			}
		}
//		printGears();

		K = Integer.parseInt(bf.readLine());
		for (int i = 0; i < K; i++) {
			tokens = new StringTokenizer(bf.readLine());
			num = Integer.parseInt(tokens.nextToken());
			dir = Integer.parseInt(tokens.nextToken());
			
			rotateAll(num - 1, dir);
//			printGears();
		}
		
		calcScore();
		System.out.println(score);
	}

}
