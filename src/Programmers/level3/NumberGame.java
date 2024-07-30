package Programmers.level3;

import java.util.Arrays;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12987">프로그래머스 - Lv.3 : 숫자 게임</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%88%AB%EC%9E%90-%EA%B2%8C%EC%9E%84">velog</a>
 * @since 2024-07-28
 */
public class NumberGame {
    public static void main(String[] args) {

        System.out.println(solution(new int[]{5, 1, 3, 7}, new int[]{2, 2, 6, 8}));
        System.out.println(solution(new int[]{2, 2, 2, 2}, new int[]{1, 1, 1, 1}));
    }

    private static int solution(int[] A, int[] B) {

        Arrays.sort(A);
        Arrays.sort(B);

        int n = A.length;

        int aIdx = 0;
        int bIdx = 0;
        int wins = 0;

        while (aIdx < n && bIdx < n) {
            if (B[bIdx] > A[aIdx]) {
                aIdx++;
                wins++;
            }
            bIdx++;
        }

        return wins;
    }
}
