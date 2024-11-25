import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        
        int n = prices.length;
        
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 0; i < n; i++){
            
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                
                int idx = stack.pop();
                
                ans[idx] = i - idx;
            }
            
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            
            int idx = stack.pop();
            
            ans[idx] = n - 1 - idx;
        }
        
        return ans;
    }
}