import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int left = 0;
        int right = n - 1;
        int max = 0;

        while (left < right) {
            int value = (right - left - 1) * Math.min(arr[left], arr[right]);
            max = Math.max(max, value);

            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(max);
    }
}