import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();
        int[] arr = new int[26];

        int num = 0;
        int ans = 0;

        for (int s = 0, e = 0; e < input.length; e++) {
            if (arr[input[e] - 'a'] == 0) {
                num++;
            }
            arr[input[e] - 'a']++;

            while (num > n) {
                arr[input[s] - 'a']--;
                if (arr[input[s] - 'a'] == 0) {
                    num--;
                }
                s++;
            }

            ans = Math.max(ans, (e - s + 1));
        }

        System.out.println(ans);
    }
}
