package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/10164">백준 10164번 - DP : 격자상의 경로</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10164%EB%B2%88-%EA%B2%A9%EC%9E%90%EC%83%81%EC%9D%98-%EA%B2%BD%EB%A1%9C">velog</a>
 * @since 2024-12-05
 */
public class BJ_10164 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int ox = -1, oy = -1;

        if (k != 0) {
            ox = (k - 1) / m;
            oy = (k - 1) % m;
        }

        //(x, y, 0) = (x, y) 위치에 O로 표시된 칸을 지나지 않은 경로 수
        //(x, y, 1) = (x, y) 위치에 O로 표시된 칸을 지나간 경로 수
        int[][][] dp = new int[n][m][2];

        dp[0][0][0] = 1;

        int[][] dir = {{1, 0}, {0, 1}};

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {

                //오른쪽 또는 아래로 이동
                for (int[] d : dir) {

                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (nx >= n || ny >= m) continue;

                    //다음 칸이 O로 표시된 칸인 경우
                    if (nx == ox && ny == oy) {
                        dp[nx][ny][1] += dp[x][y][0];
                    } else {
                        dp[nx][ny][0] += dp[x][y][0];
                        dp[nx][ny][1] += dp[x][y][1];
                    }
                }
            }
        }

        System.out.println(dp[n - 1][m - 1][(k == 0) ? 0 : 1]);
    }
}