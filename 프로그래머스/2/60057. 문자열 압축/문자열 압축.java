import java.util.*;

class Solution {
    
    public int solution(String s) {
        
        int ans = Integer.MAX_VALUE;
        
        for(int len = 1; len <= s.length(); len++) {
            
            ans = Math.min(ans, compress(s, len));
        }
        
        return ans;
    }
    
    public int compress(String s, int len) {
        
        ArrayList<String> list = new ArrayList<>();
        
        for(int i = 0; i < s.length(); i += len) {
            int j = Math.min(i + len, s.length());
            
            list.add(s.substring(i, j));
        }
        
        StringBuilder sb = new StringBuilder();
        
        String prev = "";
        int count = 0;
        
        for(String str : list) {
            if(str.equals(prev)) {
                count++;
            } 
            else {
                
                if(count >= 2) {
                    sb.append(count);
                }
                
                sb.append(prev);
                prev = str;
                count = 1;
            }
        }
        
        if(count >= 2) {
            sb.append(count);
        }
        
        sb.append(prev);
        
        return sb.length();
    }
}