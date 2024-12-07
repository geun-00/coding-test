import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        
        int[] arr = new int[rocks.length + 1];
        
        System.arraycopy(rocks, 0, arr, 0, rocks.length);
        
        arr[arr.length - 1] = distance;
        
        Arrays.sort(rocks);
        
        int low = 1;
        int high = distance;
        int ans = 0;
        
        while (low <= high) {
            
            int mid = (low + high) / 2;
            
            int removed = 0;
            int last = 0;
            
            for(int rock : rocks) {
                
                if(rock - last < mid) {
                    removed++;
                    continue;
                }
                
                last = rock;
            }
            
            if(distance - last < mid) removed++;
            
            if(removed <= n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }
}