import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int[][] arr = new int[10][10];
    static int[] paper = new int[5];
    static int ans = INF;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            paper[i] = 5;
        }

        solve(0, 0, 0);

        System.out.println(ans == INF ? -1 : ans);
    }

    private static void solve(int x, int y, int count) {
        if (x == 10) {
            ans = Math.min(ans, count);
            return;
        }

        if (y == 10) {
            solve(x + 1, 0, count);
            return;
        }

        if (arr[x][y] == 0) {
            solve(x, y + 1, count);
            return;
        }

        for (int i = 0; i < 5; i++) {

            if (paper[i] > 0 && check(x, y, i + 1)) {
                attach(x, y, i + 1, 0);
                paper[i]--;

                solve(x, y + 1, count + 1);

                attach(x, y, i + 1, 1);
                paper[i]++;
            }
        }
    }

    private static boolean check(int x, int y, int size) {
        
        if (x + size > 10 || y + size > 10) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (arr[x + i][y + j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void attach(int x, int y, int size, int num) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[x + i][y + j] = num;
            }
        }
    }

}