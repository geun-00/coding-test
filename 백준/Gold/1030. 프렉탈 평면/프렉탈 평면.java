import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");

        int s = Integer.parseInt(data[0]);
        int N = Integer.parseInt(data[1]);
        int K = Integer.parseInt(data[2]);
        int R1 = Integer.parseInt(data[3]);
        int R2 = Integer.parseInt(data[4]);
        int C1 = Integer.parseInt(data[5]);
        int C2 = Integer.parseInt(data[6]);

        StringBuilder sb = new StringBuilder();
        for (int i = R1; i <= R2; i++) {
            for (int j = C1; j <= C2; j++) {
                sb.append(solve(i, j, s, N, K));
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static String solve(int x, int y, int s, int n, int k) {
        if (s == 0) return "0";

        int size = (int) Math.pow(n, s - 1);
        int midStart = (n - k) / 2;
        int midEnd = midStart + k - 1;

        int row = (x / size) % n;
        int col = (y / size) % n;

        if (midStart <= row && row <= midEnd &&
            midStart <= col && col <= midEnd) {
            return "1";
        }

        return solve(x, y, s - 1, n, k);
    }
}