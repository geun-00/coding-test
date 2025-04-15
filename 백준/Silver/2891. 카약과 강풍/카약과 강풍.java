import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        Arrays.fill(arr, 1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) arr[Integer.parseInt(st.nextToken()) - 1]--;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) arr[Integer.parseInt(st.nextToken()) - 1]++;

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                if (i - 1 >= 0 && arr[i - 1] > 1) {
                    arr[i - 1]--;
                    arr[i]++;
                } else if (i + 1 < n && arr[i + 1] > 1) {
                    arr[i + 1]--;
                    arr[i]++;
                }
            }
        }

        int ans = 0;
        for (int num : arr) {
            if (num == 0) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
