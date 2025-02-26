import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < enemy.length; i++) {
            int e = enemy[i];

            qu.offer(e);
            n -= e;

            if (n < 0) {
                if (k > 0) {
                    n += qu.poll();
                    k--;
                } else {
                    return i;
                }
            }
        }

        return enemy.length;
    }
}