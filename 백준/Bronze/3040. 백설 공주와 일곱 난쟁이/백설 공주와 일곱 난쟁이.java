import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = 9;

        int total = 0;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (total - (arr[i] + arr[j]) == 100) {
                    arr[i] = -1;
                    arr[j] = -1;
                    break;
                }
            }
        }

        for (int i : arr) {
            if (i != -1) {
                System.out.println(i);
            }
        }
    }
}