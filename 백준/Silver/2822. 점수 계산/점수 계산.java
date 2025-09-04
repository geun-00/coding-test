import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[151];

        for (int i = 0; i < 8; i++) {
            arr[Integer.parseInt(br.readLine())] = i + 1;
        }

        int[] ans = new int[5];
        int sum = 0;

        for (int i = 150, j = 0; i >= 0 && j < 5; i--) {
            if (arr[i] != 0) {
                ans[j++] = arr[i];
                sum += i;
            }
        }

        System.out.println(sum);
        Arrays.sort(ans);

        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}