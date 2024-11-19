package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2234">백준 2034번 - 비트 마스킹 : 성곽</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2234%EB%B2%88-%EC%84%B1%EA%B3%BD">velog</a>
 *
 * @since 2024-11-14
 */
public class BJ_2234 {

    static int n, m;
    //서, 북, 동, 남
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map;
    static int[][] roomNum;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        roomNum = new int[m][n];
        visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rooms = 0;
        int max = 0;

        //최대 M x N 개의 각방이 생길 수 있음
        int[] roomsSize = new int[n * m + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    rooms++;

                    int size = bfs(i, j, rooms);
                    max = Math.max(max, size);

                    roomsSize[rooms] = size;
                }
            }
        }

        System.out.println(rooms);
        System.out.println(max);

        max = 0;
        HashSet<String> set = new HashSet<>();

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {

                for (int d = 0; d < 4; d++) {

                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                        continue;
                    }

                    int nowRoom = roomNum[x][y];
                    int nextRoom = roomNum[nx][ny];

                    if (nowRoom != nextRoom) {

                        String s = Math.min(nowRoom, nextRoom) + " " + Math.max(nowRoom, nextRoom);

                        if (set.add(s)) {

                            max = Math.max(max, roomsSize[nowRoom] + roomsSize[nextRoom]);
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }

    private static int bfs(int x, int y, int rooms) {

        visit[x][y] = true;
        roomNum[x][y] = rooms;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(x * n + y);

        int size = 0;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            int now_x = now / n;
            int now_y = now % n;
            int wall = map[now_x][now_y];

            size++;

            for (int i = 0; i < 4; i++) {

                int nx = now_x + dx[i];
                int ny = now_y + dy[i];

                if ((wall & (1 << i)) == 0 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    roomNum[nx][ny] = rooms;
                    qu.offer(nx * n + ny);
                }
            }
        }

        return size;
    }
}