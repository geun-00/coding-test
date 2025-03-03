package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16946">백준 16946번 - 그래프 탐색 : 벽 부수고 이동하기 4</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16946%EB%B2%88-%EB%B2%BD-%EB%B6%80%EC%88%98%EA%B3%A0-%EC%9D%B4%EB%8F%99%ED%95%98%EA%B8%B0-4">velog</a>
 * @since 2025-02-18
 */
public class BJ_16946 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static Map<Integer, Integer> cache = new HashMap<>();
    static int[][] temp;
    static boolean[][] visit;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visit = new boolean[n][m];
        temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '0' && !visit[i][j]) {
                    bfs(i, j, num++);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '0') {
                    sb.append('0');
                } else {
                    sb.append(searchAround(i, j));
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void bfs(int i, int j, int num) {
        visit[i][j] = true;
        temp[i][j] = num;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(i);
        qu.offer(j);

        int count = 1;

        while (!qu.isEmpty()) {
            int x = qu.poll();
            int y = qu.poll();

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visit[nx][ny] || map[nx][ny] == '1') continue;

                temp[nx][ny] = num;
                visit[nx][ny] = true;
                qu.offer(nx);
                qu.offer(ny);
                count++;
            }
        }

        cache.put(num, count);
    }

    private static int searchAround(int i, int j) {
        Set<Integer> set = new HashSet<>();

        for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == '1') {
                continue;
            }
            set.add(temp[nx][ny]);
        }

        int ans = 1;
        for (int a : set) {
            ans += cache.get(a);
            ans %= 10;
        }
        return ans;
    }
}
