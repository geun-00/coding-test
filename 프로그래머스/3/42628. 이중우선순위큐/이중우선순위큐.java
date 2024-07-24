import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (String s : operations) {
            StringTokenizer st = new StringTokenizer(s);

            String op = st.nextToken();

            if ("I".equals(op)) {
                int num = Integer.parseInt(st.nextToken());
                minHeap.offer(num);
                maxHeap.offer(num);
            } else {
                String n = st.nextToken();
                if ("1".equals(n)) {
                    if (!maxHeap.isEmpty()) {
                        minHeap.remove(maxHeap.poll());
                    }
                } else {
                    if (!minHeap.isEmpty()) {
                        maxHeap.remove(minHeap.poll());
                    }
                }
            }
        }

        if (!minHeap.isEmpty()) {
            return new int[]{maxHeap.poll(), minHeap.poll()};
        }

        return new int[]{0, 0};
    }
}