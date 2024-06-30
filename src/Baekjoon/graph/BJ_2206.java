package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2206">백준 2206번 - 그래프 탐색 : 벽 부수고 이동하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2206%EB%B2%88-%EB%B2%BD-%EB%B6%80%EC%88%98%EA%B3%A0-%EC%9D%B4%EB%8F%99%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-06-27
 */
public class BJ_2206 {

    static int[][] map;
    static boolean[][][] visit;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y, move; //좌표, 이동 횟수
        int destroy; // 0=부순 적 없음, 1=부숨

        public Point(int x, int y, int move, int destroy) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.destroy = destroy;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m][2]; //[x][x][0] = 부수지 않은 경로, [x][x][1] = 부순 경로

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(0, 0, 1, 0));
        visit[0][0][0] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            if (now.x == n - 1 && now.y == m - 1) { //도착
                return now.move;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {

                    //다음 위치가 벽이라면 전에 벽을 부순 적이 있나 확인해야 한다.
                    if (map[nx][ny] == 1) {
                        if (now.destroy == 0 && !visit[nx][ny][1]) {
                            visit[nx][ny][1] = true;
                            qu.offer(new Point(nx, ny, now.move + 1, 1));
                        }
                    //다음 위치가 벽이 아닌 경우
                    } else {
                        if (!visit[nx][ny][now.destroy]) {
                            visit[nx][ny][now.destroy] = true;
                            qu.offer(new Point(nx, ny, now.move + 1, now.destroy));
                        }
                    }
                }
            }
        }

        return -1;
    }
}
