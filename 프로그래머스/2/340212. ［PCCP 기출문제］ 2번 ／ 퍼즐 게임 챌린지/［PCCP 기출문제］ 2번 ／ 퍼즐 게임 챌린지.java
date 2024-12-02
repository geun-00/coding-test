class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        int n = diffs.length;
        
        int[] diff_arr = new int[n + 1];
        int[] time_arr = new int[n + 1];
        
        for(int i = 0; i < n; i++){
            diff_arr[i + 1] = diffs[i];
            time_arr[i + 1] = times[i];
        }    
        
        int left = 0;
        int right = Integer.MAX_VALUE;
        int ans = 0;
        
        while(left <= right) {
            
            int mid = (left + right) / 2;
            
            long result = solve(mid, diff_arr, time_arr);
            
            if(result <= limit){
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return Math.max(1, ans);
    }
    
    public long solve(int level, int[] diff_arr, int[] time_arr) {

        long ret = 0;
        
        for(int i = 1; i < diff_arr.length; i++) {
            
            if(diff_arr[i] <= level) {
                ret += time_arr[i];
            }
            else {
                int diff = diff_arr[i] - level;
                ret += time_arr[i - 1] * diff;
                ret += time_arr[i] * (diff + 1);
            }
        }
        
        return ret;
    }
}