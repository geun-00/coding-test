import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static Matrix[] matrices;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        matrices = new Matrix[n + 1];
        dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], -1);

            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            matrices[i] = new Matrix(r, c);
        }

        System.out.println(solve(1, n));
    }

    private static int solve(int s, int e) {
        int min = Integer.MAX_VALUE;

        if (dp[s][e] != -1) {
            return dp[s][e];
        }
        if (s == e) { //행렬 1개
            return 0;
        }
        if (e - s == 1) { //행렬 2개
            return matrices[s].r * matrices[e].r * matrices[e].c;
        }

        //행렬 3개 이상
        for (int i = s; i < e; i++) {
            int temp = matrices[s].r * matrices[i].c * matrices[e].c;
            min = Math.min(min, solve(s, i) + solve(i + 1, e) + temp);
        }

        return dp[s][e] = min;
    }

    static class Matrix {
        int r, c;

        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
