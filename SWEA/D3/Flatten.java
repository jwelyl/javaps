import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Flatten {
    static int T = 10, dumps, result;
    static int[] heights;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
     
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        for(int t = 1; t <= T; t++) {
            heights = new int[100];
            dumps = Integer.parseInt(bf.readLine());
             
            tokens = new StringTokenizer(bf.readLine());
            for(int i = 0; i < 100; i++)
                heights[i] = Integer.parseInt(tokens.nextToken());
             
            for(int i = 0; i < dumps; i++) { // 평탄화 작업
                int maxH = -1, minH = Integer.MAX_VALUE;    //  i+1번째 dump 전 최대높이, 최소높이
                int maxIdx = -1, minIdx = -1;               //  i+1번째 dump 전 최대높이 위치, 최소높이 위치
                 
                for(int j = 0; j < 100; j++) {   //  최대높이 찾기
                    if(maxH < heights[j]) {
                        maxH = heights[j];
                        maxIdx = j;
                    }
                     
                    if(minH > heights[j]) {  //  최소높이 찾기
                        minH = heights[j];
                        minIdx = j;
                    }
                }
                 
                heights[maxIdx] -= 1;
                heights[minIdx] += 1;
                maxH -= 1;
                minH += 1;
                 
                for(int j = 0; j < 100; j++) {   //  최대높이 찾기
                    if(maxH < heights[j]) {
                        maxH = heights[j];
                        maxIdx = j;
                    }
                     
                    if(minH > heights[j]) {  //  최소높이 찾기
                        minH = heights[j];
                        minIdx = j;
                    }
                }
                 
                result = maxH - minH;
                if(result <= 1) break;
            }
            System.out.println("#" + t + " " + result);
        }
    }
 
}