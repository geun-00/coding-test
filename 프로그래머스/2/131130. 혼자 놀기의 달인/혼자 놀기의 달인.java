import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int n = cards.length;
        boolean[] visit = new boolean[n];

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(0);

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                int num = dfs(cards, i, visit);
                pq.offer(num);
            }
        }

        return pq.poll() * pq.poll();
    }

    public int dfs(int[] cards, int index, boolean[] visit) {
        if (visit[index]) {
            return 0;
        }

        visit[index] = true;
        return 1 + dfs(cards, cards[index] - 1, visit);
    }
}