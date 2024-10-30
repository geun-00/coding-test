package Baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2098">백준 2098번 - DP : 외판원 순회</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2098%EB%B2%88-%EC%99%B8%ED%8C%90%EC%9B%90-%EC%88%9C%ED%9A%8C">velog</a>
 *
 * @since 2024-10-25
 */
public class BJ_2098 {

    static int n;
    static int[][] w;
    static long[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        w = new int[n][n];
        dp = new long[n][1 << n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(0, 1));
    }

    private static long solve(int now, int visited) {

        //N개 도시를 모두 방문한 경우, 시작 도시(0)로 돌아가야 한다.
        //가는 경로가 없으면 무한대 반환
        if (visited == (1 << n) - 1) {
            return w[now][0] == 0 ? Integer.MAX_VALUE : w[now][0];
        }

        //메모이제이션
        if (dp[now][visited] != 0) {
            return dp[now][visited];
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            //AND 연산으로 방문 체크, 이동 가능 여부 체크
            if ((visited & (1 << i)) == 0 && w[now][i] != 0) {

                //OR 연산으로 다음 방문할 도시 확인
                int next = visited | (1 << i);
                min = (int) Math.min(min, solve(i, next) + w[now][i]);

            }
        }

        return dp[now][visited] = min;
    }
}
