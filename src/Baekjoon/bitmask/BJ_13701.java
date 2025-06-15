package Baekjoon.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17244">백준 17244번 - 비트마스킹 : 아맞다우산</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17244%EB%B2%88-%EC%95%84%EB%A7%9E%EB%8B%A4%EC%9A%B0%EC%82%B0">velog</a>
 * @since 2025-05-31
 */
public class BJ_13701 {
    static int n, m;
    static char[][] map;
    static int[][] objectsIndex;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        objectsIndex = new int[n][m];
        map = new char[n][m];

        int sx = 0, sy = 0; //시작 위치
        int object = 0;     //물건의 개수

        // 1
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (map[i][j] == 'X') {
                    objectsIndex[i][j] = object++;
                }
            }
        }

        System.out.println(bfs(sx, sy, object));
    }

    // 2
    private static int bfs(int sx, int sy, int object) {
        boolean[][][] visit = new boolean[n][m][1 << object];
        visit[sx][sy][0] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{sx, sy, 0, 0});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];
            int gotObjects = now[2];    //지금까지 챙긴 물건
            int time = now[3];

            if (map[x][y] == 'E') {
                if (gotObjects == (1 << object) - 1) {
                    return time;
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (map[nx][ny] == '#') continue;

                int next = gotObjects;

                if (map[nx][ny] == 'X') {
                    int objIndex = objectsIndex[nx][ny];
                    next |= (1 << objIndex);
                }

                if (!visit[nx][ny][next]) {
                    visit[nx][ny][next] = true;
                    qu.offer(new int[]{nx, ny, next, time + 1});
                }
            }
        }

        return 0;
    }
}
