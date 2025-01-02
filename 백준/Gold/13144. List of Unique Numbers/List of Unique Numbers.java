import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine()
                                    .split(" "))
                          .mapToInt(Integer::parseInt)
                          .toArray();

        boolean[] visit = new boolean[100_001];
        int left = 0, right = 0;
        long ans = 0;

        while (left < n || right < n) {

            while (right < n && !visit[arr[right]]) {
                visit[arr[right]] = true;
                right++;
            }

            ans += (right - left);

            visit[arr[left]] = false;
            left++;
        }

        System.out.println(ans);
    }
}