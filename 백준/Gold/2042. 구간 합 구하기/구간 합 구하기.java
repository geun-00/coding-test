import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] tree;

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int size = 1;
        while ((1 << size) < n) {
            size++;
        }

        //리프 노드 시작 인덱스
        int leafNodeIdx = 1 << size;
        tree = new long[(1 << size) * 2];

        for (int i = leafNodeIdx; i < leafNodeIdx + n; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        int idx = tree.length - 1;
        while (idx > 0) {
            tree[idx / 2] += tree[idx];
            idx--;
        }

        for (int i = 0; i < m + k; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int start = b + leafNodeIdx - 1;

            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                changeValue(start, c);

            } else {
                int c = Integer.parseInt(st.nextToken());

                int end = c + leafNodeIdx - 1;
                sb.append(getSum(start, end)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static long getSum(int start, int end) {
        long sum = 0;

        while (start <= end) {
            if (start % 2 == 1) {
                sum += tree[start];
                start++;
            }
            if (end % 2 == 0) {
                sum += tree[end];
                end--;
            }
            start /= 2;
            end /= 2;
        }

        return sum;
    }

    private static void changeValue(int startIdx, long value) {
        long diff = value - tree[startIdx];
        while (startIdx > 0) {
            tree[startIdx] += diff;
            startIdx /= 2;
        }
    }
}
