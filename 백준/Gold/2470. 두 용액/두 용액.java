import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int s = 0;
        int e = n - 1;
        int temp = Integer.MAX_VALUE;

        int n1 = 0;
        int n2 = 0;

        while (s < e) {
            int sum = arr[s] + arr[e];

            if (temp > Math.abs(sum)) {
                temp = Math.abs(sum);
                n1 = s;
                n2 = e;
            }

            if (sum < 0) {
                s++;
            } else if (sum > 0) {
                e--;
            } else {
                break;
            }
        }

        System.out.println(arr[n1] + " " + arr[n2]);
    }
}
