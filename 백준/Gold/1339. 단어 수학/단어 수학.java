import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        int[] alphaWeight = new int[26];

        for (String word : words) {

            int len = word.length();

            for (int i = 0; i < len; i++) {
                char ch = word.charAt(i);

                alphaWeight[ch - 'A'] += (int) Math.pow(10, len - i - 1);
            }
        }

        Arrays.sort(alphaWeight);

        int num = 9;
        int result = 0;

        for (int i = 25; i >= 0; i--) {
            if (alphaWeight[i] == 0) {
                break;
            }

            result += alphaWeight[i] * num--;
        }

        System.out.println(result);
    }
}
