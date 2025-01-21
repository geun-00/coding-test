import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = j + 1;
            }

            List<String> ans = new ArrayList<>();
            solve(arr, 0, "", ans);

            ans.stream()
               .distinct()
               .sorted()
               .forEach(expr -> sb.append(expr).append("\n"));
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void solve(int[] arr, int index, String expr, List<String> ans) {
        if (index == arr.length) {

            expr = expr.substring(0, expr.length() - 1);

            String temp = expr.replaceAll(" ", "");
            StringTokenizer st = new StringTokenizer(temp, "+-", true);

            int result = 0;
            String lastOperation = "+";

            while (st.hasMoreTokens()) {
                String str = st.nextToken();

                if (!str.equals("+") && !str.equals("-")) {
                    result = calculate(result, lastOperation, str);
                } else {
                    lastOperation = str;
                }
            }

            if (result == 0) {
                ans.add(expr);
            }

            return;
        }

        solve(arr, index + 1, expr + arr[index] + "+", ans);
        solve(arr, index + 1, expr + arr[index] + "-", ans);
        solve(arr, index + 1, expr + arr[index] + " ", ans);
    }

    private static int calculate(int result, String lastOperation, String c) {
        int num = Integer.parseInt(c);
        if (lastOperation.equals("+")) {
            return result + num;
        }
        return result - num;
    }
}
