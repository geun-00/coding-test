package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/3109">백준 3019번 - 그리디 : 빵집</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-3019%EB%B2%88-%EB%B9%B5%EC%A7%91">velog</a>
 * @since 2024-07-28
 */
public class BJ_3109 {

    static char[][] map;
    static int r, c;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int count = 0;
        for (int i = 0; i < r; i++) {
            count += solve(i, 0);
        }

        System.out.println(count);
    }

    private static int solve(int x, int y) {

        visit[x][y] = true;

        if (y == c - 1) { //파이프라인 연결 성공
            return 1;
        }

        //오른쪽 위
        if (x - 1 >= 0 && map[x - 1][y + 1] == '.' && !visit[x - 1][y + 1]) {
            if (solve(x - 1, y + 1) == 1) {
                return 1;
            }
        }

        //오른쪽
        if (map[x][y + 1] == '.' && !visit[x][y + 1]) {
            if (solve(x, y + 1) == 1) {
                return 1;
            }
        }

        //오른쪽 아래
        if (x + 1 < r && map[x + 1][y + 1] == '.' && !visit[x + 1][y + 1]) {
            if (solve(x + 1, y + 1) == 1) {
                return 1;
            }
        }

        return 0;
    }
}
