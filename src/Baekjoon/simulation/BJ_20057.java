package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/20057">백준 20057번 - 시뮬레이션 : 마법사 상어와 토네이도</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20057%EB%B2%88-%EB%A7%88%EB%B2%95%EC%82%AC-%EC%83%81%EC%96%B4%EC%99%80-%ED%86%A0%EB%84%A4%EC%9D%B4%EB%8F%84">velog</a>
 * @since 2024-11-22
 */
public class BJ_20057 {

    static int n;
    static int ans = 0;
    static int[][] arr;
    //방향마다 모래가 이동하는 비율
    static int[][][] spread =
            {
                    {{-1, 1, 1}, {1, 1, 1}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, -1, 10}, {1, -1, 10}, {0, -2, 5}},   //왼쪽
                    {{-1, -1, 1}, {-1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {1, -1, 10}, {1, 1, 10}, {2, 0, 5}},   //아래쪽
                    {{-1, -1, 1}, {1, -1, 1}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, 1, 10}, {1, 1, 10}, {0, 2, 5}},   //오른쪽
                    {{1, -1, 1}, {1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {-1, -1, 10}, {-1, 1, 10}, {-2, 0, 5}}    //위쪽
            };
    //좌 하 우 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = n / 2;
        int y = n / 2;

        int dir = 0;
        int depth = 1;

        while (true) {

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < depth; j++) {

                    x += dx[dir];
                    y += dy[dir];

                    if (x == 0 && y == 0) {
                        moveSand(x, y, dir);
                        System.out.println(ans);
                        return;
                    }

                    moveSand(x, y, dir);
                }

                dir = (dir + 1) % 4;
            }

            depth++;
        }

    }

    private static void moveSand(int x, int y, int dir) {

        int total = arr[x][y];  //기존 모래 양
        int remain = total;     //남은 모래 양

        //정해진 비율만큼 이동
        for (int[] s : spread[dir]) {

            int nx = x + s[0];
            int ny = y + s[1];
            int moved = total * s[2] / 100;

            //범위를 벗어나면 정답에 추가, 아니면 해당 좌표에 추가
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                ans += moved;
            } else {
                arr[nx][ny] += moved;
            }

            remain -= moved;
        }

        //a로 이동하는 모래
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
            ans += remain;
        } else {
            arr[nx][ny] += remain;
        }

        arr[x][y] = 0;
    }
}