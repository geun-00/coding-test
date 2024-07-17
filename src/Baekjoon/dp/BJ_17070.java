package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17070">백준 17070번 - DP : 파이프 옮기기 1</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17070%EB%B2%88-%ED%8C%8C%EC%9D%B4%ED%94%84-%EC%98%AE%EA%B8%B0%EA%B8%B0-1">velog</a>
 * @since 2024-07-16
 */
public class BJ_17070 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] home = new int[n + 1][n + 1];
        int[][][] dp = new int[n + 1][n + 1][3]; //0=가로, 1=세로, 2=대각선

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][2][0] = 1; //초반 (1, 2)에는 가로로 도착한다.

        //1행은 가로로만 이동할 수밖에 없으므로 따로 먼저 처리한다.
        for (int i = 3; i <= n; i++) {
            if (home[1][i] == 0) {
                dp[1][i][0] = dp[1][i - 1][0];
            }
        }

        for (int x = 2; x <= n; x++) { //2행부터 n행
            for (int y = 1; y <= n; y++) { //1열부터 n열
                if (home[x][y] == 0) {

                    //(x, y) 위치에 가로로 도착하는 경우
                    dp[x][y][0] = dp[x][y - 1][0] + dp[x][y - 1][2];

                    //(x, y) 위치에 세로로 도착하는 경우
                    dp[x][y][1] = dp[x - 1][y][1] + dp[x - 1][y][2];

                    //(x, y) 위치에 대각선으로 도착하는 경우
                    if (home[x - 1][y] == 0 && home[x][y - 1] == 0) {
                        dp[x][y][2] = dp[x - 1][y - 1][0] + dp[x - 1][y - 1][1] + dp[x - 1][y - 1][2];
                    }
                }
            }
        }

        System.out.println(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);
    }
}
