package Programmers.level2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42586">프로그래머스 - Lv.2 : 기능개발</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B8%B0%EB%8A%A5%EA%B0%9C%EB%B0%9C">velog</a>
 * @since 2024-07-09
 */
public class FuncDev {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
    }

    private static int[] solution(int[] progresses, int[] speeds) {

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
