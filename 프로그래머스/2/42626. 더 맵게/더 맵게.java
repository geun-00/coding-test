import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        PriorityQueue<Integer> qu = new PriorityQueue<>();
        for (int sc : scoville) {
            qu.offer(sc);
        }

        int count = 0;

        while (!qu.isEmpty()) {

            int first = qu.poll();

            if (first >= K) {
                return count;
            }

            if(!qu.isEmpty()) {
                int second = qu.poll();

                qu.offer(first + second * 2);
                count++;
            }
            
        }

        return -1;
    }
}