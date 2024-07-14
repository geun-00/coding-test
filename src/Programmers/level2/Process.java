package Programmers.level2;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42587">프로그래머스 - Lv.2 : 프로세스</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4">velog</a>
 * @since 2024-07-12
 */
public class Process {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }

    private static int solution(int[] priorities, int location) {

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
