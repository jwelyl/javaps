import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//https://choichumji.tistory.com/119
public class Main {
	static int N;
//	static Scanner sc = new Scanner(System.in);
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static class Point implements Comparable<Point> {
		int y, x;
		Point(int x, int y) {
			this.y = y;
			this.x = x;
		}
		
		@Override
		public int compareTo(Point p) {
			if(this.y != p.y)
				return this.y - p.y;
			else
				return this.x - p.x;
		}
		
		@Override
		public String toString() {
			return x + " " + y + "\n";
		}
		
	};
	
	static Point[] points;
	static ArrayList<Point> pointArr = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		String line = bf.readLine();
		N = Integer.parseInt(line);
		
		points = new Point[N];
		
		for(int i = 0; i < N; i++) {
			line = bf.readLine();
			tokens = new StringTokenizer(line);
			
//			points[i] = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
			pointArr.add(new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		
//		Arrays.sort(points);
		Collections.sort(pointArr);
		
//		for(Point p : points)
//			sb.append(p.toString());
		for(Point p : pointArr)
			sb.append(p);
		
		System.out.println(sb.toString());
	}
}
