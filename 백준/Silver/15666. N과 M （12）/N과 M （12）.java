import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] arr;
    static int[] temp;
    static Set<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        temp = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        solve(0, 0);

        System.out.print(sb);
    }

    private static void solve(int depth, int start) {
        if (depth == m) {
            if (set.add(Arrays.toString(temp))) {

                for (int i : temp) {
                    sb.append(i).append(" ");
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = start; i < n; i++) {
            temp[depth] = arr[i];
            solve(depth + 1, i);
        }
    }
}