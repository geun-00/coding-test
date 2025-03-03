package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/18808">백준 18808번 - 시뮬레이션 : 스티커 붙이기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-18808%EB%B2%88-%EC%8A%A4%ED%8B%B0%EC%BB%A4-%EB%B6%99%EC%9D%B4%EA%B8%B0">velog</a>
 * @since 2025-02-22
 */
public class BJ_18808 {

    static int[][] laptop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Sticker[] stickers = new Sticker[k];
        laptop = new int[n][m];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] grid = new int[r][c];

            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < c; l++) {
                    grid[j][l] = Integer.parseInt(st.nextToken());
                }
            }

            stickers[i] = new Sticker(r, c, grid);
        }

        for (Sticker sticker : stickers) {

            outer:
            for (int rotate = 0; rotate < 4; rotate++) {

                for (int i = 0; i <= n - sticker.r; i++) {
                    for (int j = 0; j <= m - sticker.c; j++) {
                        if (canAttach(sticker, i, j)) {
                            attach(sticker, i, j);
                            break outer;
                        }
                    }
                }

                sticker.rotation();
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans += laptop[i][j];
            }
        }
        System.out.println(ans);
    }

    private static boolean canAttach(Sticker sticker, int x, int y) {
        int[][] grid = sticker.grid;

        for (int i = 0; i < sticker.r; i++) {
            for (int j = 0; j < sticker.c; j++) {
                if (grid[i][j] == 1 && laptop[i + x][j + y] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void attach(Sticker sticker, int x, int y) {
        int[][] grid = sticker.grid;

        for (int i = 0; i < sticker.r; i++) {
            for (int j = 0; j < sticker.c; j++) {

                laptop[i + x][j + y] = Math.max(laptop[i + x][j + y], grid[i][j]);
            }
        }
    }

    static class Sticker {
        int r, c;
        int[][] grid;

        public Sticker(int r, int c, int[][] grid) {
            this.r = r;
            this.c = c;
            this.grid = grid;
        }

        public void rotation() {
            int newR = this.c;
            int newC = this.r;

            int[][] newGrid = new int[newR][newC];

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    newGrid[j][r - 1 - i] = grid[i][j];
                }
            }

            this.grid = newGrid;
            this.r = newR;
            this.c = newC;
        }
    }
}
