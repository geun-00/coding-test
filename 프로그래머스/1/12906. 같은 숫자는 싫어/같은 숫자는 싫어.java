import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int n : arr) {
            if (!stack.isEmpty() && stack.peek() == n) {                
                continue;
            }
            stack.push(n);
        }
        
        int[] ans = new int[stack.size()];
        
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        
        return ans;
    }
}