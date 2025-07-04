import java.util.*;

class Solution {
    public int solution(int[] citations) {
        
        Arrays.sort(citations);
        
        int n = citations.length;
        
        for(int h = n; h >= 1; h--) {
            
            int idx = n - h;
            
            if(citations[idx] >= h) return h;
        }
        
        return 0;
    }
}