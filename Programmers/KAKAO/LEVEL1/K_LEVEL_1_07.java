class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        char[][] map1 = new char[n][];  //  지도 1
        char[][] map2 = new char[n][];  //  지도 2
        char[][] ans = new char[n][];   //  정답 지도
        answer = new String[n];
        
        for(int i = 0; i < n; i++) {
            map1[i] = new char[n];  
            map2[i] = new char[n];
            ans[i] = new char[n];
        }
        
        for(int i = 0; i < n; i++) {    //  지도 1 i번째 행 만들기
            int q = arr1[i], r;
            int idx = n - 1;
    
            while(q != 0) {
                r = q % 2;
                q = q / 2;
               
                if(r == 0)
                    map1[i][idx] = ' ';
                else
                    map1[i][idx] = '#';
                idx--;
            }
            if(idx > 0) {
                idx--;
                map1[i][idx] = ' ';
            }
        }
        
        for(int i = 0; i < n; i++) {
            int q = arr2[i], r;
            int idx = n - 1;
    
            while(q != 0) {
                r = q % 2;
                q = q / 2;
                
                if(r == 0)
                    map2[i][idx] = ' ';
                else
                    map2[i][idx] = '#';
                idx--;
            }
            if(idx > 0) {
                idx--;
                map2[i][idx] = ' ';
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map1[i][j] == '#' || map2[i][j] == '#')
                    ans[i][j] = '#';
                else
                    ans[i][j] = ' ';
            }
        }
        
        
        
        for(int i = 0; i < n; i++) {
            String str = new String(ans[i]);
            answer[i] = str;
        }
        
        return answer;
    }
}