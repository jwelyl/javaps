import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
       
        HashMap<String, Integer> hash1 = new HashMap<>();
        HashMap<String, Integer> hash2 = new HashMap<>();
        
        for(String p : participant) {
        	if(hash1.containsKey(p)) {
        		hash1.put(p, hash1.get(p) + 1);
        	}
        	else
        		hash1.put(p, 1);
        }
        
        for(String c : completion) {
        	if(hash2.containsKey(c)) {
        		hash2.put(c, hash2.get(c) + 1);
        	}
        	else
        		hash2.put(c, 1);
        }
        
        for(String p : participant) {
        	if(!hash2.containsKey(p))
        		answer = p;
        	else if(hash2.get(p) < hash1.get(p))
        		answer = p;
        }
        
        return answer;
    }
}
