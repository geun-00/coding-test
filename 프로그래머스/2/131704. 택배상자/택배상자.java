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

            //아직 순서가 아닌 상자 보조 컨테이너 벨트에 보관
            for (int i = num; i < now; i++, num++) {
                stack.push(i);
            }

            //트럭에 실어야 하는 순서라면
            if (num == now) {
                num++;
                ans++;
            }
            //보조 컨테이너 벨트에 마지막으로 보관했던 물건이 순서에 맞다면        
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