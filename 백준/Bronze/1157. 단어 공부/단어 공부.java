import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[26];
        String s = br.readLine().toUpperCase();

        int max = 0;
        for (char c : s.toCharArray()) {
            arr[c - 'A']++;
            max = Math.max(max, arr[c - 'A']);
        }

        int count = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == max) {
                count++;
                index = i;
            }
        }

        if (count > 1) {
            System.out.println("?");
            return;
        }

        System.out.println((char) (index + 'A'));
    }
}
