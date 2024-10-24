import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        arr = new int[n + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[n + 1] = l;

        Arrays.sort(arr);

        int left = 1;
        int right = l;

        int result = 0;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (check(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();

        bw.close();
        br.close();
    }

    private static boolean check(int val) {

        int count = 0;

        for (int i = 1; i <= n + 1; i++) {
            int dist = arr[i] - arr[i - 1];
            count += (dist - 1) / val;
        }

        return count <= m;
    }
}
