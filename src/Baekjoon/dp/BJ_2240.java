package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2240">백준 2240번 - DP : 자두나무</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2240%EB%B2%88-%EC%9E%90%EB%91%90%EB%82%98%EB%AC%B4">velog</a>
 * @since 2024-11-03
 */
public class BJ_2240 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] tree = new int[t + 1];
        for (int i = 1; i <= t; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[t + 1][w + 1][3];

        //1초 초깃값 설정
        if (tree[1] == 1) {
            dp[1][0][1] = 1;
        } else {
            dp[1][1][2] = 1;
        }

        for (int i = 2; i <= t; i++) {

            //1번 나무에서 자두가 떨어질 때
            if (tree[i] == 1) {

                dp[i][0][1] = dp[i - 1][0][1] + 1; //1번에 있으면 이전 위치에서 움직이지 않고 1개를 받을 수 있다.
                dp[i][0][2] = dp[i - 1][0][2];     //2번에 있으면 움직이지 않으면 받을 수 없다.

                //1번~w번 움직이는 동안 받는 자두 최대 개수를 구한다.
                for (int j = 1; j <= w; j++) {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]) + 1;
                    dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i - 1][j - 1][1]);
                }
            }
            //2번 나무에서 자두가 떨어질 때
            else {
                dp[i][0][1] = dp[i - 1][0][1];      //1번에 있으면 움직이지 않으면 받을 수 없다.
                dp[i][0][2] = dp[i - 1][0][2] + 1;  //2번에 있으면 이전 위치에서 움직지이 않고 1개를 받을 수 있다.

                //1번~w번 움직이는 동안 받는 자두 최대 개수를 구한다.
                for (int j = 1; j <= w; j++) {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]);
                    dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i - 1][j - 1][1]) + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i <= w; i++) {
            max = Math.max(max, Math.max(dp[t][i][1], dp[t][i][2]));
        }

        System.out.print(max);
    }
}