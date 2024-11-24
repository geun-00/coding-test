package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/9376">백준 9376번 - 다익스트라 : 탈옥</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9376%EB%B2%88-%ED%83%88%EC%98%A5">velog</a>
 * @since 2024-11-22
 */
public class BJ_9376 {

    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int h, w;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        //두 명의 죄수 위치
        int[] points = new int[2];

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            //기본 크기 확장
            h = Integer.parseInt(st.nextToken()) + 2;
            w = Integer.parseInt(st.nextToken()) + 2;

            map = new char[h][w];

            for (int i = 0; i < h; i++) {
                Arrays.fill(map[i], '.');
            }

            int idx = 0;

            for (int i = 1; i <= h - 2; i++) {
                char[] arr = br.readLine().toCharArray();

                for (int j = 1; j <= w - 2; j++) {

                    map[i][j] = arr[j - 1];

                    if (map[i][j] == '$') {
                        points[idx++] = i * w  + j;
                        map[i][j] = '.';    //BFS에 영향이 안 가도록 변경
                    }
                }
            }

            int[][] arr1 = bfs(points[0]);  //죄수 1
            int[][] arr2 = bfs(points[1]);  //죄수 2
            int[][] arr3 = bfs(0);       //상근이

            int min = Integer.MAX_VALUE;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {

                    if (map[i][j] == '*') continue;

                    int sum = arr1[i][j] + arr2[i][j] + arr3[i][j];

                    if (map[i][j] == '#') {
                        sum -= 2;
                    }

                    min = Math.min(min, sum);
                }
            }

            sb.append(min).append("\n");
        }

        System.out.print(sb);
    }

    private static int[][] bfs(int p) {

        int[][] arr = new int[h][w];
        boolean[][] visit = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            Arrays.fill(arr[i], 9999);
        }

        int x = p / w;
        int y = p % w;

        arr[x][y] = 0;
        visit[x][y] = true;

        Deque<Integer> qu = new ArrayDeque<>();
        qu.offer(p);

        while (!qu.isEmpty()) {

            int now = qu.poll();
            x = now / w;
            y = now % w;

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];
                int next = nx * w + ny;

                if (nx < 0 || ny < 0 || nx >= h || ny >= w || map[nx][ny] == '*' || visit[nx][ny]) {
                    continue;
                }

                visit[nx][ny] = true;

                if (map[nx][ny] == '.') {
                    arr[nx][ny] = arr[x][y];
                    qu.offerFirst(next);
                }
                else if (map[nx][ny] == '#') {
                    arr[nx][ny] = arr[x][y] + 1;
                    qu.offerLast(next);
                }
            }
        }

        return arr;
    }
}