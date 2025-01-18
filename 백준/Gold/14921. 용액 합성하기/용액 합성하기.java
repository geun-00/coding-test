import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] temp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        temp = new int[n];

        int[] arr = Arrays.stream(br.readLine().split(" "))
                          .mapToInt(Integer::parseInt)
                          .toArray();

        Arrays.sort(arr);

        int ans = Integer.MAX_VALUE;
        int left = 0;
        int right = n - 1;

        while (left < right) {

            int diff = arr[right] + arr[left];

            if (Math.abs(diff) < Math.abs(ans)) {
                ans = diff;
            }

            if (diff == 0) break;

            if (diff < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(ans);
    }
}