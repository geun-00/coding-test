package Programmers.level3;

import java.util.Arrays;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12938">프로그래머스 - Lv.3 : 최고의 집합</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%B5%9C%EA%B3%A0%EC%9D%98-%EC%A7%91%ED%95%A9">velog</a>
 * @since 2024-07-26
 */
public class BestSet {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(2, 9)));
        System.out.println(Arrays.toString(solution(2, 1)));
        System.out.println(Arrays.toString(solution(2, 8)));
    }

    private static int[] solution(int n, int s) {
        if (n > s) {
            return new int[]{-1};
        }

        int[] result = new int[n];

        int base = s / n;
        int remain = s % n;

        Arrays.fill(result, base);

        for (int i = 0; i < remain; i++) {
            result[i]++;
        }

        Arrays.sort(result);

        return result;
    }
}
