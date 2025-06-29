import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> tasks = new ArrayDeque<>();
        int[] priority = new int[10];

        for (int i = 0; i < priorities.length; i++) {
            tasks.offer(new int[]{i, priorities[i]});
            priority[priorities[i]]++;
        }

        int ans = 0;

        loop:
        while (!tasks.isEmpty()) {
            int[] task = tasks.poll();

            for (int i = task[1] + 1; i < 10; i++) {
                if (priority[i] > 0) {
                    tasks.offer(task);
                    continue loop;
                }
            }

            priority[task[1]]--;
            ans++;

            if (task[0] == location) {
                return ans;
            }
        }

        return ans;
    }
}