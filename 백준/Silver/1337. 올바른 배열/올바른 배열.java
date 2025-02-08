import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 0, right = 0;
        int len = 0;

        while (right < n) {

            while (arr[right] - arr[left] >= 5) {
                left++;
            }

            len = Math.max(len, right - left + 1);
            right++;
        }

        System.out.println(5 - len);
    }
}