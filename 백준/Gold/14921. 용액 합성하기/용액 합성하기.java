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

        int ans = Integer.MAX_VALUE;

        int left = 0;
        int right = n - 1;

        while (left < right) {

            int diff = arr[right] + arr[left];

            if (Math.abs(diff) < Math.abs(ans)) ans = diff;

            if (diff == 0) break;

            if (diff < 0) left++;
            else right--;
        }

        System.out.println(ans);
    }
}