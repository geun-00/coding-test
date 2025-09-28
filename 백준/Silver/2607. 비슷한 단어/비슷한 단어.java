import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String s = br.readLine();
        int[] arr = new int[26];

        for (char c : s.toCharArray()) {
            arr[c - 'A']++;
        }

        int ans = 0;

        for (int i = 0; i < n - 1; i++) {
            int[] temp = new int[26];
            String word = br.readLine();

            for (char c : word.toCharArray()) {
                temp[c - 'A']++;
            }

            int diff = 0;
            for (int j = 0; j < 26; j++) {
                diff += Math.abs(arr[j] - temp[j]);
            }

            if (diff <= 1 || (diff == 2 && s.length() == word.length())) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}