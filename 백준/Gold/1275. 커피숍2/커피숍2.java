import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int level = 1;
        while ((1 << level) < n) {
            level++;
        }

        int leafNodeIdx = 1 << level;
        tree = new long[leafNodeIdx * 2];

        st = new StringTokenizer(br.readLine());

        for (int i = leafNodeIdx; i < leafNodeIdx + n; i++) {
            tree[i] = Long.parseLong(st.nextToken());
        }

        int index = tree.length - 1;
        while (index > 0) {
            tree[index / 2] += tree[index];
            index--;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            
            if (x > y) {
                int temp = x;
                x = y;
                y = temp;
            }

            x = x + leafNodeIdx - 1;
            y = y + leafNodeIdx - 1;
            a = a + leafNodeIdx - 1;

            sb.append(getSum(x, y)).append("\n");
            changeValue(a, b);
        }

        System.out.print(sb);
    }

    private static void changeValue(int index, long value) {

        long diff = value - tree[index];

        while (index > 0) {
            tree[index] += diff;
            index /= 2;
        }
    }

    private static long getSum(int s, int e) {

        long sum = 0;

        while (s <= e) {
            if (s % 2 == 1) {
                sum += tree[s];
                s++;
            }
            if (e % 2 == 0) {
                sum += tree[e];
                e--;
            }
            s /= 2;
            e /= 2;
        }

        return sum;
    }
}