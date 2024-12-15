import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        long[] layer = new long[51];
        long[] patties = new long[51];

        layer[0] = 1;
        patties[0] = 1;

        for (int i = 1; i <= n; i++) {
            layer[i] = (layer[i - 1] * 2) + 3;
            patties[i] = (patties[i - 1] * 2) + 1;
        }

        System.out.println(solve(n, x, layer, patties));
    }

    private static long solve(int level, long x, long[] layer, long[] patties) {
        if (level == 0) {
            return 1;
        }

        long prevLayer = layer[level - 1];

        if (x == 1) {
            return 0;
        } else if (x <= prevLayer + 1) {
            return solve(level - 1, x - 1, layer, patties);
        } else if (x == prevLayer + 2) {
            return patties[level - 1] + 1;
        } else {
            return patties[level - 1] + 1 + solve(level - 1, x - prevLayer - 2, layer, patties);
        }
    }
}
