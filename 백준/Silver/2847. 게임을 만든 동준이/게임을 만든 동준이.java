import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= arr[i + 1]) {

                int target = arr[i + 1] - 1;

                count += arr[i] - target;
                arr[i] = target;
            }
        }

        System.out.println(count);
    }
}