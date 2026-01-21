class Solution {
    public long solution(int n, int[] times) {
        long low = 0;
        long high = Long.MAX_VALUE;
        long answer = 0;
        
        while (low <= high) {
            long mid = (low + high) / 2;
            
            long count = 0;
            
            for (int t : times) {
                count += (mid / t);
                if (count >= n) break;
            }
            
            if (count >= n) {
                high = mid - 1;
                answer = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return answer;
    }
}