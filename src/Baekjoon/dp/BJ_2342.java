package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <a href = "https://www.acmicpc.net/problem/2342">백준 2342번 - DP : Dance Dance Revolution</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2342%EB%B2%88-Dance-Dance-Revolution">velog</a>
 *
 * @since 2024-11-25
 */
public class BJ_2342 {

    public static final int INF = 999_999;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //i 에서 j 로 이동할 때 드는 힘 초기화
        int[][] arr = {
                {0, 2, 2, 2, 2},
                {2, 1, 3, 4, 3},
                {2, 3, 1, 3, 4},
                {2, 4, 3, 1, 3},
                {2, 3, 4, 3, 1},
        };

        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = input.length;

        int[][][] dp = new int[n][5][5];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        dp[0][0][0] = 0;

        int idx = 0;

        while (input[idx] != 0) {

            //이동 방향
            int point = input[idx];

            for (int i = 0; i < 5; i++) {   //왼발의 위치
                for (int j = 0; j < 5; j++) {   //오른발의 위치

                    //이동 가능한 경로만 탐색
                    if (dp[idx][i][j] == INF) continue;

                    //오른발을 움직이는 경우
                    if (point != i) {   //두 발이 같은 위치에 있는 경우는 보지 않는다.

                        dp[idx + 1][i][point] = Math.min(
                                dp[idx + 1][i][point],
                                dp[idx][i][j] + arr[j][point]
                        );
                    }

                    //왼발을 움직이는 경우
                    if (point != j) {   //두 발이 같은 위치에 있는 경우는 보지 않는다.

                        dp[idx + 1][point][j] = Math.min(
                                dp[idx + 1][point][j],
                                dp[idx][i][j] + arr[i][point]
                        );
                    }
                }
            }

            idx++;
        }

        int ans = INF;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(dp[idx][i][j], ans);
            }
        }

        System.out.println(ans);
    }
}