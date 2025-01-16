import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] order) {
        
        Queue<Integer> qu = Arrays.stream(order)
                                  .boxed()
                                  .collect(Collectors.toCollection(ArrayDeque::new));

        Deque<Integer> stack = new ArrayDeque<>();
        int num = 1;
        int ans = 0;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int i = num; i < now; i++, num++) {
                stack.push(i);
            }

            if (num == now) {
                num++;
                ans++;
            }
            else if (!stack.isEmpty() && stack.peek() == now) {
                stack.pop();
                ans++;
            }
            else {
                break;
            }
        }

        return ans;
    }
}