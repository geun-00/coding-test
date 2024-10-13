package Baekjoon.graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/9205">백준 9205번 - 그래프 탐색 : 맥주 마시면서 걸어가기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9205%EB%B2%88-%EB%A7%A5%EC%A3%BC-%EB%A7%88%EC%8B%9C%EB%A9%B4%EC%84%9C-%EA%B1%B8%EC%96%B4%EA%B0%80%EA%B8%B0">velog</a>
 *
 * @since 2024-10-09
 */
public class BJ_9205 {

    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());

            Point[] points = new Point[n + 2];
            graph = new ArrayList[n + 2];

            for (int i = 0; i < n + 2; i++) {

                graph[i] = new ArrayList<>();

                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                points[i] = new Point(x, y);
            }

            for (int s = 0; s <= n; s++) {
                for (int e = s + 1; e <= n + 1; e++) {

                    int dist = Math.abs(points[s].x - points[e].x) + Math.abs(points[s].y - points[e].y);

                    if (dist <= 1000) {
                        graph[s].add(e);
                        graph[e].add(s);
                    }
                }
            }

            sb.append(bfs(n)).append("\n");
        }

        System.out.print(sb);
    }

    private static String bfs(int n) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(0);

        boolean[] visit = new boolean[n + 2];
        visit[0] = true;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            if (now == n + 1) {
                return "happy";
            }

            for (int next : graph[now]) {
                if (!visit[next]) {
                    visit[next] = true;
                    qu.offer(next);
                }
            }
        }

        return "sad";
    }
}
