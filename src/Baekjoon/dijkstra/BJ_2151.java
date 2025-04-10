package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <a href = "https://www.acmicpc.net/problem/2151">백준 2151번 - 다익스트라 : 거울 설치</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2151%EB%B2%88-%EA%B1%B0%EC%9A%B8-%EC%84%A4%EC%B9%98">velog</a>
 *
 * @since 2024-10-13
 */
public class BJ_2151 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[][] map = new char[n][n];
        int[][][] dist = new int[n][n][4];

        int sx = 0, sy = 0;
        int ex = 0, ey = 0;
        boolean first = true;

        for (int i = 0; i < n; i++) {

            char[] arr = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {

                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
                map[i][j] = arr[j];

                if (map[i][j] == '#') {
                    if (first) {
                        first = false;
                        sx = i;
                        sy = j;
                    } else {
                        ex = i;
                        ey = j;
                    }
                }
            }
        }

        PriorityQueue<Node> qu = new PriorityQueue<>();

        for (int i = 0; i < 4; i++) {
            qu.offer(new Node(sx, sy, i, 0));
            dist[sx][sy][i] = 0;
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            if (now.x == ex && now.y == ey) {
                System.out.println(now.count);
                return;
            }

            int nx = now.x + dx[now.dir];
            int ny = now.y + dy[now.dir];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n && map[nx][ny] != '*') {

                //거울을 설치하지 않는 경우
                if (dist[nx][ny][now.dir] > now.count) {
                    dist[nx][ny][now.dir] = now.count;
                    qu.offer(new Node(nx, ny, now.dir, now.count));
                }

                if (map[nx][ny] == '!') {

                    int nDir;

                    //오른쪽으로 꺾는 경우
                    nDir = (now.dir + 1) % 4;
                    if (dist[nx][ny][nDir] > now.count + 1) {
                        dist[nx][ny][nDir] = now.count + 1;
                        qu.offer(new Node(nx, ny, nDir, now.count + 1));
                    }

                    //왼쪽으로 꺾는 경우
                    nDir = (now.dir + 3) % 4;
                    if (dist[nx][ny][nDir] > now.count + 1) {
                        dist[nx][ny][nDir] = now.count + 1;
                        qu.offer(new Node(nx, ny, nDir, now.count + 1));
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node> {

        int x, y, dir, count;

        public Node(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}
