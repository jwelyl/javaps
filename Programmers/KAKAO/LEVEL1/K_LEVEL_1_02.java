import java.util.*;

class Solution {
    Set<Character> charSet = new HashSet<>();   //  숫자, 알파벳 제외 가능한 문자 집합
    
    void init() {   //  '-', '_', '.'는 가능
        charSet.add('-');
        charSet.add('_');
        charSet.add('.');
    }
    
    public String solution(String new_id) {
        String answer = "";
        init();
        
        //  phase 1
        char[] str = new_id.toCharArray(); //  new_id 문자열을 char 배열로 변경
        for(int i = 0; i < str.length; i++) {
            char ch = str[i];
            
            if('A' <= ch && ch <= 'Z') {    //  대문자일 경우
                ch = (char)(ch + ('a' - 'A'));  //  소문자로 변경
                str[i] = ch;
            }
        }
        
        //  phase 2
        new_id = "";
        for(int i = 0; i < str.length; i++) {
            if(('0' <= str[i] && str[i] <= '9') || 
                    ('a' <= str[i] && str[i] <= 'z')) {
                new_id += str[i];  //  숫자거나 소문자일 경우 그대로 둠
                continue;
            }
            else if(charSet.contains(str[i])) {    //  숫자, 문자 제외 포함 가능 문자일 경우
                new_id += str[i];  //  그대로 둠
                continue;
            }
        }
        
        //  phase 3
        str =  new_id.toCharArray();
        new_id = "";
        for(int i = 0; i < str.length;) {
            if(str[i] == '.') { //  '.'이 시작되는 위치 i
                int j = i + 1;
                new_id += '.';  //  '.' 하나만 추가
                
                while(j < str.length && str[j] == '.')  //  '.'이 끝날때까지 이동
                    j++;
                
                i = j;
            }
            else {
                new_id += str[i];   //  '.'이 아닐 경우 추가
                i +=1;
            }
        }
        
        //  phase 4
        str = new_id.toCharArray();
        new_id = "";
        int from = 0, to = str.length - 1;
        
        if(str[from] == '.')    //  맨 처음이 '.'일 경우
            from = from + 1;
        if(str[to] == '.')  //  맨 끝이 '.'일 경우
            to = to - 1;
        if(!(from >= str.length || to < 0)) {
            for(int i = from ; i <= to; i++)
                new_id += str[i];
        }
      
        //  phase 5  
        if(new_id == "")
            new_id = "a";
    
        if(new_id.length() >= 16) { //  phase 6
            if(new_id.charAt(14) == '.')
                new_id = new_id.substring(0, 14);
            else
                new_id = new_id.substring(0, 15);
        }
        else if(new_id.length() <= 2) { //  phase 7
            char ch = new_id.charAt(new_id.length() - 1);
            
            while(new_id.length() < 3) {
                new_id = new_id + ch;
            }
        }
        
        answer = new_id;
        return answer;
    }
}