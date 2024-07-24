package Programmers.level3;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42628">프로그래머스 - Lv.3 : 이중우선순위큐</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%9D%B4%EC%A4%91%EC%9A%B0%EC%84%A0%EC%88%9C%EC%9C%84%ED%81%90">velog</a>
 * @since 2024-07-22
 */
public class DoubleQueue {
    public static void main(String[] args) {

        System.out.println("result1 = " + Arrays.toString(solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})));
        System.out.println("result2 = " + Arrays.toString(solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"})));
    }

    private static int[] solution(String[] operations) {

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
