import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : scoville) {
            pq.offer(n);
        }
        
        while (pq.size() > 1 && pq.peek() < K) {
            int a = pq.poll();
            int b = pq.poll();
            
            pq.offer(a + (b * 2));
            answer++;
        }
        
        if (!pq.isEmpty() && pq.peek() < K) {
            return -1;
        }
        
        return answer;
    }
}