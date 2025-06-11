class Solution {
    public String solution(int a, int b) {
        int[] arr = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
        int day = arr[a - 1] + b - 1;
        
        String[] ans = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        
        return ans[day % 7];
    }
}