package Programmers.level3;

import java.util.Arrays;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/152995">프로그래머스 - Lv.3 : 인사고과</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%9D%B8%EC%82%AC%EA%B3%A0%EA%B3%BC">velog</a>
 * @since 2024-08-16
 */
public class HRDepartment {
    public static void main(String[] args) {

        System.out.println(solution(new int[][]{
                {2, 2},
                {1, 4},
                {3, 2},
                {3, 2},
                {2, 1},
        }));
    }

    private static int solution(int[][] scores) {

        int[] wanho = scores[0];
        int wanhoSum = wanho[0] + wanho[1];

        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        int result = 1;

        int max = scores[0][1];

        for (int[] score : scores) {

            if (max > score[1]) {
                if (Arrays.equals(score, wanho)) {
                    return -1;
                }
            } else {
                max = score[1];

                int sum = score[0] + score[1];
                if (sum > wanhoSum) {
                    result++;
                }
            }
        }

        return result;
    }
}
