class Solution {
    int ans = 0;
    public int solution(int[] numbers, int target) {
        dfs(0, numbers, target, 0);
        return ans;
    }
    
    public void dfs(int num, int[] numbers, int target, int depth) {
        if (depth == numbers.length) {
            if (num == target) {
                ans++;
            }
            return;
        }
        
        dfs(num + numbers[depth], numbers, target, depth + 1);
        dfs(num - numbers[depth], numbers, target, depth + 1);
    }
}