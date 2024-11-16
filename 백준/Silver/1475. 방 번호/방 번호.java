import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[10];

        for (char c : br.readLine().toCharArray()) {
            arr[c - '0']++;
        }

        arr[6] = (arr[6] + arr[9] + 1) / 2;

        int max = arr[0];

        for (int i = 1; i < 9; i++) {
            max = Math.max(max, arr[i]);
        }

        System.out.println(max);
    }
}