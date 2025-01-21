package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/20058">백준 20058번 - 시뮬레이션 : 마법사 상어와 파이어스톰</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20058%EB%B2%88-%EB%A7%88%EB%B2%95%EC%82%AC-%EC%83%81%EC%96%B4%EC%99%80-%ED%8C%8C%EC%9D%B4%EC%96%B4%EC%8A%A4%ED%86%B0">velog</a>
 * @since 2025-01-12
 */
public class BJ_20058 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] arr = new int[1 << n][1 << n];

        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < q; i++) {
            int l = Integer.parseInt(st.nextToken());

            arr = rotate(arr, l);
            decrease(arr);
        }

        System.out.println(getSum(arr));
        System.out.println(getMaxSize(arr));
    }

    private static int[][] rotate(int[][] arr, int l) {

        int n = arr.length;

        int[][] newArr = new int[n][n];
        int size = 1 << l;

        for (int i = 0; i < n; i += size) {
            for (int j = 0; j < n; j += size) {

                for (int x = 0; x < size; x++) {
                    for (int y = 0; y < size; y++) {
                        newArr[i + y][j + size - 1 - x] = arr[i + x][j + y];
                    }
                }
            }
        }

        return newArr;
    }

    private static void decrease(int[][] arr) {

        int n = arr.length;
        boolean[][] mark = new boolean[n][n];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {

                int count = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || arr[nx][ny] <= 0) {
                        continue;
                    }
                    count++;
                }

                if (count < 3) {
                    mark[x][y] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mark[i][j]) {
                    arr[i][j]--;
                }
            }
        }
    }

    private static int getSum(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                sum += Math.max(arr[i][j], 0);
            }
        }
        return sum;
    }

    private static int getMaxSize(int[][] arr) {

        int n = arr.length;
        boolean[][] visit = new boolean[n][n];

        int maxSize = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] > 0 && !visit[i][j]) {
                    maxSize = Math.max(maxSize, bfs(i, j, arr, visit));
                }
            }
        }

        return maxSize;
    }

    private static int bfs(int i, int j, int[][] arr, boolean[][] visit) {

        visit[i][j] = true;
        int n = arr.length;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(i);
        qu.offer(j);

        int size = 1;

        while (!qu.isEmpty()) {

            int x = qu.poll();
            int y = qu.poll();

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visit[nx][ny] || arr[nx][ny] <= 0) {
                    continue;
                }

                size++;
                visit[nx][ny] = true;
                qu.offer(nx);
                qu.offer(ny);
            }
        }

        return size;
    }
}