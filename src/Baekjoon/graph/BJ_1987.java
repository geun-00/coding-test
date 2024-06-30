package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1987">백준 1987번 - 그래프 탐색 : 알파벳</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1987%EB%B2%88-%EC%95%8C%ED%8C%8C%EB%B2%B3">velog</a>
 * @since 2024-06-30
 */
public class BJ_1987 {

    static char[][] map;
    static int r, c;
    static boolean[] visit = new boolean[26];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int max = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        dfs(0, 0, 1);

        System.out.println(max);
    }

    private static void dfs(int x, int y, int depth) {

        max = Math.max(max, depth);
        visit[map[x][y] - 'A'] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < r && ny < c && !visit[map[nx][ny] - 'A']) {

                dfs(nx, ny, depth + 1);
            }
        }

        visit[map[x][y] - 'A'] = false;
    }
}
