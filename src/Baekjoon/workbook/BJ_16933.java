package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16933">백준 16933번 - 벽 부수고 이동하기 3</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16933%EB%B2%88-%EB%B2%BD-%EB%B6%80%EC%88%98%EA%B3%A0-%EC%9D%B4%EB%8F%99%ED%95%98%EA%B8%B0-3">velog</a>
 * @since 2025-03-26
 */
public class BJ_16933 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 1
        boolean[][][] visit = new boolean[n][m][k + 1];
        visit[0][0][0] = true;

        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(0, 0, 0, 1));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            Node cur = qu.poll();

            // 2
            int x = cur.x;
            int y = cur.y;
            int wall = cur.wall;
            int dist = cur.dist;
            boolean isAM = (dist % 2 == 1);

            if (x == n - 1 && y == m - 1) {
                System.out.println(dist);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                // 3
                if (map[nx][ny] == '0') {
                    if (!visit[nx][ny][wall]) {
                        visit[nx][ny][wall] = true;
                        qu.offer(new Node(nx, ny, wall, dist + 1));
                    }
                }
                // 4
                else {
                    if (wall + 1 <= k) {
                        if (isAM) { //낮
                            if (!visit[nx][ny][wall + 1]) {
                                visit[nx][ny][wall + 1] = true;
                                qu.offer(new Node(nx, ny, wall + 1, dist + 1));
                            }
                        }
                        else {  //밤
                            qu.offer(new Node(x, y, wall, dist + 1));
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }

    static class Node {
        int x, y, wall, dist;

        public Node(int x, int y, int wall, int dist) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.dist = dist;
        }
    }
}
