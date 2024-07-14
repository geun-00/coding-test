import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        
        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        for (int p : priorities) {
            qu.offer(p);
        }

        int result = 0;

        while (!qu.isEmpty()) {

            for (int i = 0; i < priorities.length; i++) {
                if (!qu.isEmpty() && qu.peek() == priorities[i]) {
                    qu.poll();
                    result++;

                    if (i == location) {
                        return result;
                    }
                }
            }
        }

        return result;
    }
}