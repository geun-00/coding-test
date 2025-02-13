import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        int n = queue1.length;

        long current = 0;
        long total = 0;

        Queue<Integer> qu1 = new ArrayDeque<>();
        Queue<Integer> qu2 = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            current += queue1[i];
            total += queue1[i] + queue2[i];

            qu1.offer(queue1[i]);
            qu2.offer(queue2[i]);
        }

        if (total % 2 != 0) return -1;

        long target = total / 2;

        int ans = 0;
        while (ans <= n * 4) {

            if (current == target) return ans;

            if (current < target) {
                current += qu2.peek();
                qu1.offer(qu2.poll());
            } else {
                current -= qu1.peek();
                qu2.offer(qu1.poll());
            }

            ans++;
        }

        return -1;
    }
}