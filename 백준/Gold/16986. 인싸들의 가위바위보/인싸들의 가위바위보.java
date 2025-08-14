import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] info, arr;
    static int n, k, ans;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[3][20];

        info = new int[n][n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 20; i++) {
            arr[1][i] = Integer.parseInt(st.nextToken()) - 1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 20; i++) {
            arr[2][i] = Integer.parseInt(st.nextToken()) - 1;
        }

        solve(0);
        System.out.println(ans);
    }

    private static void solve(int depth) {
        if (depth == n) {
            if (ans == 0) {
                simulate(0, 1, new int[3], new int[3]);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                arr[0][depth] = i;
                solve(depth + 1);

                visit[i] = false;
            }
        }
    }

    private static void simulate(int a, int b, int[] index, int[] wins) {
        if (ans == 1) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (wins[i] >= k) {
                if (i == 0) {
                    ans = 1;
                }
                return;
            }
        }

        if (index[0] >= n) return;

        int t1 = arr[a][index[a]];
        int t2 = arr[b][index[b]];

        int result = info[t1][t2];

        int win;

        if (result == 2) {
            win = a;
        } else if (result == 0) {
            win = b;
        } else {
            win = Math.max(a, b);
        }

        index[a]++;
        index[b]++;
        wins[win]++;

        int next = 3 - a - b;

        simulate(win, next, index, wins);

        index[a]--;
        index[b]--;
        wins[win]--;
    }
}