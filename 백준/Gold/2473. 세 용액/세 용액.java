import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long[] result = new long[3];
        long diff = Long.MAX_VALUE;

        loop:
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                long sum = arr[i] + arr[left] + arr[right];

                if (Math.abs(sum) < diff) {
                    diff = Math.abs(sum);
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];
                }

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    break loop;
                }
            }
        }

        Arrays.sort(result);
        for (int i = 0; i < 3; i++) {
            System.out.print(result[i] + " ");
        }
    }
}