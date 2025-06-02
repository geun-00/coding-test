class Solution {
    public int[] solution(long begin, long end) {
        int[] ans = new int[(int) (end - begin + 1)];
        
        for (long i = begin; i <= end; i++) {
            long block = 1;
            
            for (long j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    if (i / j <= 10_000_000) {
                        block = (int) i / j;
                    } else {
                        block = (int) j;
                        continue;
                    }
                    
                    break;
                }                                
            }
            
            if (begin == 1) ans[0] = 0;
            
            ans[(int) (i - begin)] = (int) block;
        }
        
        return ans;
    }
}