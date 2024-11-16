import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[][] board;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int max = 0;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (x + 1 < n) {
                    swap(x, y, x + 1, y);
                    max = Math.max(max, check());
                    swap(x, y, x + 1, y);
                }
                if (y + 1 < n) {
                    swap(x, y, x, y + 1);
                    max = Math.max(max, check());
                    swap(x, y, x, y + 1);
                }
            }
        }

        System.out.println(max);
    }

    private static int check() {

        int ret = 0;

        for (int i = 0; i < n; i++) {
            int same = 1;
            for (int j = 0; j < n - 1; j++) {

                if (board[i][j] == board[i][j + 1]) {
                    same++;
                } else {
                    same = 1;
                }
                ret = Math.max(ret, same);
            }

            same = 1;
            for (int j = 0; j < n - 1; j++) {

                if (board[j][i] == board[j + 1][i]) {
                    same++;
                } else {
                    same = 1;
                }
                ret = Math.max(ret, same);
            }
        }

        return ret;
    }

    private static void swap(int x1, int y1, int x2, int y2) {
        char temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }
}