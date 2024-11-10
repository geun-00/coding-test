import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int n;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            int[] pick = new int[i];
            solve(0, 0, i, pick);
        }

        System.out.println(min);
    }

    private static void solve(int start, int depth, int pickNum, int[] pick) {
        if (depth == pickNum) {
            min = Math.min(min, getDiff(pick));
            return;
        }

        for (int i = start; i < n; i++) {
            pick[depth] = i;
            solve(i + 1, depth + 1, pickNum, pick);
        }
    }

    private static int getDiff(int[] pick) {

        int s = 1;
        int b = 0;

        for (int i = 0; i < pick.length; i++) {
            s *= arr[pick[i]][0];
            b += arr[pick[i]][1];
        }

        return Math.abs(s - b);
    }
}