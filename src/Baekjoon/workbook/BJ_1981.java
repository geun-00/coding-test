package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1981">백준 1981번 - 배열에서 이동</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1981%EB%B2%88-%EB%B0%B0%EC%97%B4%EC%97%90%EC%84%9C-%EC%9D%B4%EB%8F%99">velog</a>
 * @since 2025-03-15
 */
public class BJ_1981 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        // 1
        int min = 200;
        int max = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        // 2
        int left = 0, right = max - min;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (solve(mid, max)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }

    // 3
    private static boolean solve(int limit, int max) {
        for (int minVal = 0; minVal <= max - limit; minVal++) {
            int maxVal = minVal + limit;
            if (bfs(minVal, maxVal)) return true;
        }

        return false;
    }

    // 4
    private static boolean bfs(int minVal, int maxVal) {
        if (arr[0][0] < minVal || arr[0][0] > maxVal) return false;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(0);
        qu.offer(0);

        boolean[][] visit = new boolean[n][n];

        while (!qu.isEmpty()) {
            int x = qu.poll();
            int y = qu.poll();

            if (x == n - 1 && y == n - 1) return true;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visit[nx][ny] || arr[nx][ny] < minVal || arr[nx][ny] > maxVal) continue;

                qu.offer(nx);
                qu.offer(ny);
                visit[nx][ny] = true;
            }
        }

        return false;
    }
}
