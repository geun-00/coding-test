package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1445">백준 1445번 - 다익스트라 : 일요일 아침의 데이트</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1445%EB%B2%88-%EC%9D%BC%EC%9A%94%EC%9D%BC-%EC%95%84%EC%B9%A8%EC%9D%98-%EB%8D%B0%EC%9D%B4%ED%8A%B8">velog</a>
 *
 * @since 2024-11-03
 */
public class BJ_1445 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;
    static char[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        int x = 0;
        int y = 0;

        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = arr[j];

                if (map[i][j] == 'S') {
                    x = i;
                    y = j;
                }
            }
        }

        int[] ans = dijkstra(x, y);

        System.out.println(ans[0] + " " + ans[1]);
    }

    private static int[] dijkstra(int x, int y) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(x, y, 0, 0));

        boolean[][] visit = new boolean[n][m];

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            if (visit[now.x][now.y]) continue;
            visit[now.x][now.y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                int ng = now.g;
                int nag = now.ag;

                if (map[nx][ny] == 'F') {
                    return new int[]{ng, nag};
                }

                if (map[nx][ny] == 'g') {
                    ng++;
                } else {
                    if (check(nx, ny)) {
                        nag++;
                    }
                }

                qu.offer(new Node(nx, ny, ng, nag));
            }
        }

        return null;
    }

    private static boolean check(int x, int y) {

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }

            if (map[nx][ny] == 'g') {
                return true;
            }
        }

        return false;
    }

    static class Node implements Comparable<Node> {

        int x, y;   //위치
        int g, ag;  //쓰레기를 지나간 개수, 쓰레기 옆을 지나간 개수

        public Node(int x, int y, int g, int ag) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.ag = ag;
        }

        @Override
        public int compareTo(Node o) {
            if (this.g == o.g) {
                return this.ag - o.ag;
            }
            return this.g - o.g;
        }
    }
}
