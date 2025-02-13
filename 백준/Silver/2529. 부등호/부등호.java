import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    static int k;
    static String[] arr;
    static boolean[] used = new boolean[10];
    static int[] select;
    static String min = "9999999999";
    static String max = "0000000000";

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        arr = new String[k];
        select = new int[k + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = st.nextToken();
        }

        solve(0);
        System.out.println(max);
        System.out.println(min);
    }

    private static void solve(int depth) {
        if (depth == k + 1) {
            String result = Arrays.stream(select)
                                  .mapToObj(String::valueOf)
                                  .collect(Collectors.joining());
            if (min.compareTo(result) > 0) min = result;
            if (max.compareTo(result) < 0) max = result;
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!used[i]) {

                if (depth > 0) {
                    int prev = select[depth - 1];
                    String operation = arr[depth - 1];

                    if ("<".equals(operation) && prev > i) continue;
                    if (">".equals(operation) && prev < i) continue;
                }

                select[depth] = i;
                used[i] = true;
                solve(depth + 1);
                used[i] = false;
            }
        }
    }
}