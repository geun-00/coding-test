package Programmers.level3;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12927">프로그래머스 - Lv.3 : 야근 지수</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%95%BC%EA%B7%BC-%EC%A7%80%EC%88%98">velog</a>
 * @since 2024-07-25
 */
public class Overtime {
    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{4, 3, 3}));
        System.out.println(solution(1, new int[]{2, 1, 2}));
        System.out.println(solution(3, new int[]{1, 1}));
    }

    private static long solution(int n, int[] works) {

        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        for (int w : works) {
            qu.offer(w);
        }

        for (int i = 0; i < n; i++) {

            if (qu.isEmpty() || qu.peek() == 0) {
                return 0;
            }

            qu.offer(qu.poll() - 1);
        }

        long sum = 0;

        for (int num : qu) {
            sum += (long) num * num;
        }

        return sum;
    }
}
