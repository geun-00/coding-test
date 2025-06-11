import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[max] - '0' < arr[j] - '0') {
                    max = j;
                }
            }

            if (max != i) {
                char temp = arr[max];
                arr[max] = arr[i];
                arr[i] = temp;
            }
        }

        System.out.println(new String(arr));
    }
}