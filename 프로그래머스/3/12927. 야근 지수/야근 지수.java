import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        for (int w : works) {
            qu.offer(w);
        }

        for (int i = 0; i < n; i++) {

            if (qu.peek() == 0) {
                return 0;
            }

            qu.offer(qu.poll() - 1);
        }

        long sum = 0;

        while (!qu.isEmpty()) {
            int num = qu.poll();
            sum += (long) num * num;
        }

        return sum;
    }
}