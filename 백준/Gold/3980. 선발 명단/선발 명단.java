import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int ans;
    static boolean[] placed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            int[][] arr = new int[11][11];
            ans = 0;
            placed = new boolean[11];

            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < 11; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solve(0, arr, 0);
            System.out.println(ans);
        }
    }

    private static void solve(int depth, int[][] arr, int sum) {
        if (depth >= 11) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (arr[depth][i] > 0 && !placed[i]) {
                placed[i] = true;
                solve(depth + 1, arr, sum + arr[depth][i]);
                placed[i] = false;
            }
        }
    }
}