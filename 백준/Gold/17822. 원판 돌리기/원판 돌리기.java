import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int n, m;
    static int remain;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        remain = n * m;

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < t; i++) {

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int r = x - 1; r < n; r += x) {
                rotate(arr[r], d, k);
            }

            if (remain > 0) {

                if (find()) continue;

                double avg = getAvg();

                solve(avg);
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans += arr[i][j];
            }
        }

        System.out.println(ans);
    }

    private static boolean find() {

        boolean[][] remove = new boolean[n][m];
        boolean ret = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (arr[i][j] == 0) continue;

                if (arr[i][j] == arr[i][(j + 1) % m]) {
                    remove[i][j] = true;
                    remove[i][(j + 1) % m] = true;
                }

                if (i < n - 1) {

                    if (arr[i][j] == arr[i + 1][j]) {
                        remove[i][j] = true;
                        remove[i + 1][j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (remove[i][j]) {
                    remain--;
                    ret = true;
                    arr[i][j] = 0;
                }
            }
        }

        return ret;
    }

    private static void solve(double avg) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (arr[i][j] == 0) continue;

                if (arr[i][j] < avg) {
                    arr[i][j]++;
                }
                else if (arr[i][j] > avg) {
                    arr[i][j]--;
                }
            }
        }
    }

    private static double getAvg() {

        double sum = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (arr[i][j] == 0) continue;

                    count++;
                    sum += arr[i][j];

            }
        }

        return sum / count;
    }

    private static void rotate(int[] row, int d, int k) {

        int[] temp = new int[m];

        for (int i = 0; i < m; i++) {

            int idx = (d == 0)
                    ? (i + k) % m
                    : (i - k + m) % m;

            temp[idx] = row[i];
        }

        for (int i = 0; i < m; i++) {
            row[i] = temp[i];
        }
    }
}