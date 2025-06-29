import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        Deque<Integer> qu = new ArrayDeque<>();
        
        for(int num : arr) {
            
            while(!qu.isEmpty() && qu.peekLast() == num){
                qu.pollLast();
            }
            
            qu.offer(num);
        }
        
        int[] ans = new int[qu.size()];
        
        int idx = 0;
        
        while(!qu.isEmpty()) {
            ans[idx++] = qu.poll();
        }
        
        return ans;
    }
}