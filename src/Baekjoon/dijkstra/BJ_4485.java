package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/4485">백준 4485번 - 다익스트라 : 녹색 옷 입은 애가 젤다지?</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-4485%EB%B2%88-%EB%85%B9%EC%83%89-%EC%98%B7-%EC%9E%85%EC%9D%80-%EC%95%A0%EA%B0%80-%EC%A0%A4%EB%8B%A4%EC%A7%80">velog</a>
 *
 * @since 2024-07-06
 */
public class BJ_4485 {

    static int[][] cave;
    static int[][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int num = 1;

        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == 0) {
                break;
            }

            cave = new int[n][n];
            dist = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra();

            sb.append("Problem ")
                    .append(num++)
                    .append(": ")
                    .append(dist[n - 1][n - 1])
                    .append("\n");
//            sb.append("Problem ").append(num++).append(": ").append(bfs()).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(0, 0, cave[0][0]));

        boolean[][] visit = new boolean[n][n];
        dist[0][0] = cave[0][0];

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            int x = now.x;
            int y = now.y;
            int d = now.d;

            if (visit[x][y]) {
                continue;
            }
            visit[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {

                    int newD = d + cave[nx][ny];

                    if (dist[nx][ny] > newD) {

                        dist[nx][ny] = newD;
                        qu.offer(new Node(nx, ny, newD));
                    }
                }
            }

        }
    }

    private static int bfs() {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        boolean[][] visit = new boolean[n][n];

        visit[0][0] = true;
        qu.offer(new Node(0, 0, cave[0][0]));

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            if (now.x == n - 1 && now.y == n - 1) {
                return now.d;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    qu.offer(new Node(nx, ny, now.d + cave[nx][ny]));
                }
            }
        }

        return -1;
    }

    static class Node implements Comparable<Node> {
        int x, y, d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }
}
