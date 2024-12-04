class Solution {
    public int solution(int[] citations) {
        
        for(int h = citations.length; h >= 1; h--) {
            
            int count = 0;
            
            for(int c : citations) {
                if(c >= h) count++;
            }
            
            if(count >= h) return h;
        }
        
        return 0;
    }
}