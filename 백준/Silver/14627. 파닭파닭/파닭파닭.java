import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[s];
        for (int i = 0; i < s; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long low = 1;
        long high = (long) 1e9;
        long result = 0;

        while (low <= high) {
            long mid = (low + high) / 2;

            long count = 0;
            for (int i : arr) {
                count += (i / mid);
            }

            if (count >= c) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        long sum = 0;
        for (int len : arr) {
            sum += len;
        }

        System.out.println(sum - (result * c));
    }
}