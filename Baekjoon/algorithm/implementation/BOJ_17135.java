import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * @author koreii
 * @see https://www.acmicpc.net/problem/
 * @difficuly	G3
 * @performance 39520KB 396ms
 * @category # 구현, 시뮬레이션
 * @memo 이전에 못풀어서 다시 풀어봄. 괜찮은 컨디션에서 집중해서 푸니까 그렇게 어렵진 않았음.
 * @memo 소기능 별로 분리해서 따로 따로 천천히, 그러나 완벽하게 구현하여 조합해서 사용하면 다 됨.
 * @memo 문제 조건을 빠뜨리지 말고 풀면 다 풀림.
 * @memo Set, Map 같은 Collection에서 Key로 객체를 사용하려면 equals뿐만 아니라 hashCode도 overriding해야 함.
 * @memo Overriding은 Eclipse에서 기본적으로 만들어주는 Overriding 이용하면 될듯. 
 * @etc 정 모르겠으면 printMap 등으로 중간중간 디버깅해보기. 완탐 문제 빨리 풀어서 시간을 벌자.
 * 
 */

public class BOJ_17135 {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M, D;
	static int[][] originalMap; // 원본 맵(한번 입력 받으면 변하지 않음)
	static int[][] copyMap; // 복제 맵(시뮬레이션에 사용될 맵)

	static int originalEnemyCnt = 0;	//	원래 적 개수
	static int enemyCnt;
	static int[] archerPos = new int[3]; // 궁수 3명의 x 좌표 (0 <= x1 < x2 < x3 < M)
	static int kills = 0; // 궁수 배치 경우에 따른 kill 수
	static int maxKills = -1; // 최대 kill 수
	

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		D = Integer.parseInt(tokens.nextToken());

		originalMap = new int[N][M];
		copyMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				originalMap[i][j] = Integer.parseInt(tokens.nextToken());
				if(originalMap[i][j] == 1)
					originalEnemyCnt++;
			}
		}

		comb(0, 0);
		System.out.println(maxKills);
	}

	// 원본 맵을 복사 맵에 복사
	static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				copyMap[i][j] = originalMap[i][j];
		}
	}

	// 두 좌표의 거리 구하기
	static int mDist(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}

	// 궁수 세 명의 좌표 설정 (N, x1), (N, x2), (N, x3) (0 <= x1 < x2 < x3 < M)
	static void comb(int nth, int startIdx) {
		if (nth == 3) { // 3명의 좌표 정해진 경우

//			System.out.println("선택된 궁수 좌표");
//			for (int i = 0; i < 3; i++)
//				System.out.print("(" + N + ", " + archerPos[i] + ") ");
//			System.out.println();

			enemyCnt = originalEnemyCnt;
			kills = 0; // 킬 수 초기화
			copy(); // 원본 맵을 복사함

			while (!isEnd()) { // 모든 적이 제외되기 전까지
				killEnemies(); // 한 턴 적을 죽임
				moveDown(); // 적이 내려옴
			}

//			System.out.println("최종 kill 수 = " + kills);
			if (kills > maxKills)
				maxKills = kills;

			return;
		}

		for (int i = startIdx; i < M; i++) {
			archerPos[nth] = i;
			comb(nth + 1, i + 1);
		}
	}

	// 모든 적이 제외되었는지 check
	static boolean isEnd() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++)
//				if (copyMap[i][j] == 1)
//					return false;
//		}

//		return true;
		return (enemyCnt == 0);
	}

	static class Pos {
		int y;
		int x;

		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pos other = (Pos) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[" + y + ", " + x + "]";
		}
		
		
	}

	// 궁수 세 명이 적을 죽임
	static void killEnemies() {
		List<Pos> enemyList = new ArrayList<>(); // 모든 적의 조표
		Set<Pos> killedEnemies = new HashSet<>(); // 죽임을 당한 적의 좌표 모음

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 1)
					enemyList.add(new Pos(i, j)); // 적 위치 삽입
			}
		}

		for (int i = 0; i < 3; i++) {
			int archerY = N;
			int archerX = archerPos[i]; // i번째 궁수 좌표

			int resultDist = Integer.MAX_VALUE; // 최종으로 죽일 적의 거리
			int resultEnemyY = Integer.MAX_VALUE; // 최종으로 죽일 적의 y 좌표
			int resultEnemyX = Integer.MAX_VALUE; // 최종으로 죽일 적의 x 좌표

			for (Pos p : enemyList) {
				int enemyY = p.y;
				int enemyX = p.x;

				int dist = mDist(archerY, archerX, enemyY, enemyX);
				if (dist <= D && dist < resultDist) { // 거리가 D 미만이고 가장 가까운 적일 경우
					resultDist = dist;
					resultEnemyY = enemyY;
					resultEnemyX = enemyX;
				} else if (dist <= D && dist == resultDist) { // 거리가 가장 가까운 적이 여럿일 경우
					if (enemyX < resultEnemyX) { // 이번 적이 왼쪽일 경우
						resultEnemyY = enemyY;
						resultEnemyX = enemyX;
					}
				}
			}

			// 실제 죽일 적 집합에 추가
			if (resultDist != Integer.MAX_VALUE) // 실제 죽일 적을 찾은 경우
				killedEnemies.add(new Pos(resultEnemyY, resultEnemyX));
		}

//		System.out.println("사살할 적 목록");
//		System.out.println(killedEnemies);
		for (Pos p : killedEnemies) {
			int y = p.y;
			int x = p.x;
			copyMap[y][x] = 0; // 적을 죽여서 게임에서 제외시킴
			kills++; // 죽인 수 1 증가
			enemyCnt--;
		}

//		printMap("적 사살한 후");
//		System.out.println("누적 kill 수 = " + kills);
	}

	// 모든 적들이 한칸씩 내려옴
	static void moveDown() {
		for (int w = 0; w < M; w++) {
			for (int h = N - 1; h >= 0; h--) {
				if (copyMap[h][w] == 0) // 빈 칸일 경우 skip
					continue;

				if (h == N - 1) {// 성 바로 윗칸일 경우
					copyMap[h][w] = 0; // 성에 들어가서 제외됨
					enemyCnt--;
				}
				else { // 아래로 한 칸씩 이동
					copyMap[h + 1][w] = copyMap[h][w];
					copyMap[h][w] = 0;
				}
			}
		}

//		printMap("적 한칸씩 내려온 후");
	}

	static void printMap(String msg) {
		System.out.println(msg);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				System.out.print(copyMap[i][j] + " ");
			System.out.println();
		}
	}
}
