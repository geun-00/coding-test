package Programmers.level2;

import java.util.Arrays;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/87390">프로그래머스 - Lv.2 : n^2 배열 자르기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-n2-%EB%B0%B0%EC%97%B4-%EC%9E%90%EB%A5%B4%EA%B8%B0">velog</a>
 * @since 2024-07-03
 */
public class N2Array {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, 2, 5)));
        System.out.println(Arrays.toString(solution(4, 7, 14)));
    }

    private static int[] solution(int n, long left, long right) {

        int len = (int) (right - left + 1);
        int[] result = new int[len];

        for (long i = left; i <= right; i++) {
            int row = (int) (i / n); //행
            int col = (int) (i % n); //열

            result[(int) (i - left)] = Math.max(row, col) + 1;
        }

        return result;

/*
        int[][] arr = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Math.max(i, j);
            }
        }

        int[] temp = new int[n * n];

        int index = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                temp[index++] = arr[i][j];
            }
        }

        int[] result = new int[(int) (right - left + 1)];

        index = 0;
        for (long i = left; i <= right; i++) {
            result[index++] = temp[(int) i];
        }

        return result;
*/
    }
}
