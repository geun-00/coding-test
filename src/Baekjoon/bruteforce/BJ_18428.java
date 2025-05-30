package Baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/18428">백준 18428번 - 브루트포스 : 감시 피하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-18428%EB%B2%88-%EA%B0%90%EC%8B%9C-%ED%94%BC%ED%95%98%EA%B8%B0">velog</a>
 * @since 2025-02-17
 */
public class BJ_18428 {

    static int n;
    static String[][] grid;
    static String ans = "NO";
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        grid = new String[n][n];

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().split(" ");
        }

        solve(0);
        System.out.println(ans);
    }

    public static void solve(int depth) {
        if (ans.equals("YES")) {
            return;
        }

        if (depth == 3) {
            checkPossible();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j].equals("X")) {
                    grid[i][j] = "O";
                    solve(depth + 1);
                    grid[i][j] = "X";
                }
            }
        }
    }

    public static void checkPossible() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j].equals("T")) {

                    for (int d = 0; d < 4; d++) {
                        if (!go(i, j, d)) return;
                    }
                }
            }
        }

        ans = "YES";
    }

    public static boolean go(int x, int y, int dir) {
        while (x >= 0 && y >= 0 && x < n && y < n && !grid[x][y].equals("O")) {
            if (grid[x][y].equals("S")) {
                return false;
            }
            x += dx[dir];
            y += dy[dir];
        }

        return true;
    }
}
