import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    static long[] tree;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int level = 1;
        while ((1 << level) < n) {
            level++;
        }

        int leafNodeIdx = 1 << level;
        tree = new long[leafNodeIdx * 2];

        Arrays.fill(tree, 1);

        for (int i = leafNodeIdx; i < leafNodeIdx + n; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        int idx = tree.length - 1;

        while (idx > 0) {
            tree[idx / 2] *= tree[idx];
            tree[idx / 2] %= MOD;
            idx--;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            b = b + leafNodeIdx - 1;
            if (a == 1) {
                changeValue(b, c);
            } else {
                c = c + leafNodeIdx - 1;

                sb.append(getResult(b, c)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static int getResult(int s, int e) {
        long result = 1;

        while (s <= e) {
            if (s % 2 == 1) {
                result *= tree[s];
                result %= MOD;
                s++;
            }
            if (e % 2 == 0) {
                result *= tree[e];
                result %= MOD;
                e--;
            }
            s /= 2;
            e /= 2;
        }

        return (int) result;
    }

    private static void changeValue(int idx, int value) {
        tree[idx] = value;
        while (idx > 0) {
            idx /= 2;
            tree[idx] = (tree[idx * 2] % MOD) * (tree[idx * 2 + 1] % MOD);
            tree[idx] %= MOD;
        }
    }
}