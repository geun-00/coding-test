package Baekjoon.graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2589">백준 2589번 - 그래프 탐색 : 보물섬</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2589%EB%B2%88-%EB%B3%B4%EB%AC%BC%EC%84%AC">velog</a>
 * @since 2024-08-06
 */
public class BJ_2589 {

    static char[][] map;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }

        //마지막 육지에서 의미 없는 탐색이 한번 있기 때문에 -1 한 값이 정답
        System.out.println(result - 1);
    }

    private static void bfs(int x, int y) {

        Queue<Point> qu = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][m];

        qu.offer(new Point(x, y));
        visit[x][y] = true;

        int count = 0;

        while (!qu.isEmpty()) {

            int size = qu.size();
            count++;

            for (int i = 0; i < size; i++) {
                Point now = qu.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                        if (!visit[nx][ny] && map[nx][ny] == 'L') {
                            qu.offer(new Point(nx, ny));
                            visit[nx][ny] = true;
                        }
                    }
                }
            }
        }

        result = Math.max(result, count);
    }
}
