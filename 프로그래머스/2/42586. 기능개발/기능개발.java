import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
       Queue<Integer> qu = new ArrayDeque<>();

        for (int i = 0; i < speeds.length; i++) {
            qu.offer((int) Math.ceil((100.0 - progresses[i]) / speeds[i]));
        }

        ArrayList<Integer> list = new ArrayList<>();

        while (!qu.isEmpty()) {
            int temp = qu.poll();

            int count = 1;

            while (!qu.isEmpty() && qu.peek() <= temp) {
                qu.poll();
                count++;
            }

            list.add(count);
        }
        
        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}