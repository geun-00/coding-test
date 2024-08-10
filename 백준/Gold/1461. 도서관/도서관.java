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
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int sum = 0;
        int max = Math.max(Math.abs(arr[0]), Math.abs(arr[n - 1]));

        for (int i = 0; i < n; i += m) {
            if (arr[i] < 0) {
                sum += Math.abs(arr[i] * 2);
            } else {
                break;
            }
        }

        for (int i = n - 1; i >= 0; i -= m) {
            if (arr[i] > 0) {
                sum += Math.abs(arr[i] * 2);
            } else {
                break;
            }
        }

        System.out.println(sum - max);
    }
}
