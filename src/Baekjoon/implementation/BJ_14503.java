package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14503">백준 14502번 - 구현 : 로봇 청소기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14503%EB%B2%88-%EB%A1%9C%EB%B4%87-%EC%B2%AD%EC%86%8C%EA%B8%B0">velog</a>
 * @since 2024-06-25
 */
public class BJ_14503 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        int x = r;
        int y = c;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (true) {
            if (map[x][y] == 0) { //현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
                map[x][y] = 2;
                count++;
            }

            boolean flag = false;
            for (int i = 0; i < 4; i++) { //현재 칸 주변 4칸 탐색
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (map[nx][ny] == 0) { //청소되지 않은 빈 칸이 있는 경우
                    flag = true;
                    break;
                }
            }

            if (!flag) { //청소되지 않은 빈 칸이 없는 경우, 바라보는 방향을 본 채로 후진해야 한다.
                int nx = 0, ny = 0;

                switch (d) { //바라보는 방향에 따라 후진할 다음 위치 구함
                    case 0:
                        nx = x + 1;
                        ny = y;
                        break;
                    case 1:
                        nx = x;
                        ny = y - 1;
                        break;
                    case 2:
                        nx = x - 1;
                        ny = y;
                        break;
                    case 3:
                        nx = x;
                        ny = y + 1;
                }

                if (map[nx][ny] == 1) { //뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                    break;
                } else { //후진할 수 있으면 한 칸 후진
                    x = nx;
                    y = ny;
                }
            } else {     //청소되지 않은 빈 칸이 있는 경우, 반시계 방향으로 돌면서 청소되지 않은 빈 칸을 찾아야 한다.

                int nx = 0, ny = 0;

                for (int i = 0; i < 4; i++) {
                    d = (d + 3) % 4; //반시계 방향 90도 회전

                    switch (d) {
                        case 0:
                            nx = x - 1;
                            ny = y;
                            break;
                        case 1:
                            nx = x;
                            ny = y + 1;
                            break;
                        case 2:
                            nx = x + 1;
                            ny = y;
                            break;
                        case 3:
                            nx = x;
                            ny = y - 1;
                    }

                    if (map[nx][ny] == 0) { //가장 먼저 만나는 청소되지 않은 빈 칸으로 전진한다.
                        x = nx;
                        y = ny;
                        break;
                    }
                }
            }
        }

        System.out.println(count);
    }
}
