class Solution {
    
    public int solution(int n) {
        return solve(0, 0, n);
    }

    public int solve(int open, int close, int n) {
        if (open == n && close == n) {
            return 1;
        }
        if (open > n || open < close) {
            return 0 ;
        }

        return solve(open + 1, close, n) + solve(open, close + 1, n);
    }
}