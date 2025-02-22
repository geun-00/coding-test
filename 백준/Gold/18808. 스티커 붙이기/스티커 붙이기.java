import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static Sticker[] stickers;
    static int[][] laptop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        stickers = new Sticker[k];
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
        int r = sticker.r;
        int c = sticker.c;
        int ix = 0, iy = 0;

        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {

                if (grid[ix][iy] == 1 && laptop[i][j] == 1) {
                    return false;
                }
                iy++;
            }
            ix++;
            iy = 0;
        }

        return true;
    }

    private static void attach(Sticker sticker, int x, int y) {
        int[][] grid = sticker.grid;
        int r = sticker.r;
        int c = sticker.c;
        int ix = 0, iy = 0;

        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {

                laptop[i][j] = Math.max(laptop[i][j], grid[ix][iy]);
                iy++;
            }
            ix++;
            iy = 0;
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
            int temp = this.r;
            this.r = this.c;
            this.c = temp;

            int[][] newGrid = new int[r][c];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    newGrid[j][grid.length - 1 - i] = grid[i][j];
                }
            }

            this.grid = newGrid;
        }
    }
}
