import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static boolean[] visit;
    static int[] order;
    static int[][] arr;
    static int[][] rotateOperation;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visit = new boolean[k];
        order = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotateOperation = new int[k][3];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            rotateOperation[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotateOperation[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rotateOperation[i][2] = Integer.parseInt(st.nextToken());
        }

        solve(0);

        System.out.println(ans);
    }

    private static void solve(int depth) {
        if (depth == k) {
            rotate();
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visit[i]) {
                visit[i] = true;
                order[depth] = i;
                solve(depth + 1);
                visit[i] = false;
            }
        }
    }

    private static void rotate() {

        int[][] copyArr = getCopyArr();

        for (int o : order) {

            int r = rotateOperation[o][0];
            int c = rotateOperation[o][1];
            int s = rotateOperation[o][2];

            for (int i = 1; i <= s; i++) {

                int ltx = r - i;    //Left Top X
                int lty = c - i;    //Left Top Y

                int rtx = r - i;    //Right Top Y
                int rty = c + i;    //Right Top Y

                int lbx = r + i;    //Left Bottom X
                int lby = c - i;    //Left Bottom Y

                int rbx = r + i;    //Right Bottom X
                int rby = c + i;    //Right Bottom Y

                int temp = copyArr[ltx][lty];

                for (int j = ltx; j < lbx; j++) {
                    copyArr[j][lty] = copyArr[j + 1][lty];
                }

                for (int j = lby; j < rby; j++) {
                    copyArr[lbx][j] = copyArr[lbx][j + 1];
                }

                for (int j = rbx; j > rtx; j--) {
                    copyArr[j][rty] = copyArr[j - 1][rty];
                }

                for (int j = rty; j > lty; j--) {
                    copyArr[ltx][j] = copyArr[ltx][j - 1];
                }

                copyArr[ltx][lty + 1] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += copyArr[i][j];
            }
            ans = Math.min(ans, sum);
        }
    }

    private static int[][] getCopyArr() {
        int[][] temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        return temp;
    }
}
