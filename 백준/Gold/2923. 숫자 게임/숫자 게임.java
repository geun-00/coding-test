import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] A = new int[101];
        int[] B = new int[101];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[Integer.parseInt(st.nextToken())]++;
            B[Integer.parseInt(st.nextToken())]++;

            sb.append(getMax(A, B, i + 1))
              .append("\n");
        }

        System.out.print(sb);
    }

    private static int getMax(int[] arr1, int[] arr2, int total) {
        int[] x = Arrays.copyOf(arr1, arr1.length);
        int[] y = Arrays.copyOf(arr2, arr2.length);
        int xIdx = 1;
        int yIdx = 100;
        int max = 0;
        int used = 0;

        while (used < total) {
            while (xIdx <= 100 && x[xIdx] == 0) xIdx++;
            while (yIdx >= 1 && y[yIdx] == 0) yIdx--;

            if (xIdx > 100 || yIdx < 1) break;

            int use = Math.min(x[xIdx], y[yIdx]);
            use = Math.min(use, total - used);

            max = Math.max(max, xIdx + yIdx);

            x[xIdx] -= use;
            y[yIdx] -= use;
            used += use;
        }
        return max;
    }
}
