package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/11780">백준 11780번 - 다익스트라 : 플로이드 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-11780%EB%B2%88-%ED%94%8C%EB%A1%9C%EC%9D%B4%EB%93%9C-2">velog</a>
 * @since 2025-02-06
 */
public class BJ_11780 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int inf = (int) 1e9;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] dist = new int[n + 1][n + 1];
        int[][] path = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                dist[i][j] = inf;
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (dist[a][b] > c) {
                dist[a][b] = c;
                path[a][b] = b;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (dist[s][e] > dist[s][k] + dist[k][e]) {
                        dist[s][e] = dist[s][k] + dist[k][e];
                        path[s][e] = path[s][k];
                    }
                }
            }
        }

        StringBuilder ans = new StringBuilder();

        //최소 비용 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ans.append(dist[i][j] == inf ? 0 : dist[i][j])
                   .append(" ");
            }
            ans.append("\n");
        }

        //경로 출력
        for (int s = 1; s <= n; s++) {
            for (int e = 1; e <= n; e++) {

                //출발지=도착지 이거나, 아예 경로가 없는 경우
                if (s == e || dist[s][e] == inf) {
                    ans.append(0).append("\n");
                    continue;
                }

                List<Integer> cities = new ArrayList<>();

                //도착지에 도착할 때까지 거쳐가는 경로 저장
                int now = s;
                while (now != e) {
                    cities.add(now);
                    now = path[now][e];
                }

                cities.add(e); //도착지 저장
                ans.append(cities.size()).append(" ");

                for (int city : cities) {
                    ans.append(city).append(" ");
                }
                ans.append("\n");
            }
        }

        System.out.print(ans);
    }
}
