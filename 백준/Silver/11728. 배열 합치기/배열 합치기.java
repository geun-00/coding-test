import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        int[] b = new int[m];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int a_point = 0, b_point = 0;
        int index = 0;

        int[] ans = new int[n + m];

        while (a_point < n && b_point < m) {
            if (a[a_point] < b[b_point]) {
                ans[index++] = a[a_point++];
            } else {
                ans[index++] = b[b_point++];
            }
        }

        while (a_point < n) {
            ans[index++] = a[a_point++];
        }

        while (b_point < m) {
            ans[index++] = b[b_point++];
        }

        StringBuilder sb = new StringBuilder();
        for (int i : ans) {
            sb.append(i).append(" ");
        }

        System.out.print(sb);
    }
}