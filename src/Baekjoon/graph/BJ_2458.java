package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2458">백준 2458번 - 그래프 탐색 : 키 순서</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2458%EB%B2%88-%ED%82%A4-%EC%88%9C%EC%84%9C">velog</a>
 * @since 2024-11-20
 */
public class BJ_2458 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            arr[b][a] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    if (arr[s][k] == 1 && arr[k][e] == 1) {
                        arr[s][e] = 1;
                    }
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1 || arr[j][i] == 1) {
                    count++;
                }
            }

            if (count == n - 1) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
