import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> qu = new ArrayDeque<>();
        
        for (int i = 0; i < speeds.length; i++) {
            int p = progresses[i];
            int s = speeds[i];
            int r = ((100 - p) + s - 1) / s;
            
            qu.offer(r);
        }
        
        List<Integer> list = new ArrayList<>();
        
        while (!qu.isEmpty()) {
            int count = 1;
            int prev = qu.poll();
            
            while (!qu.isEmpty() && qu.peek() <= prev) {
                count++;
                qu.poll();
            }
            
            list.add(count);
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
}