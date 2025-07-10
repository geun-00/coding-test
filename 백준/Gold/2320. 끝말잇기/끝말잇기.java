import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static String[] words;
    static int n;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        words = new String[n];
        memo = new int[n][1 << n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            Arrays.fill(memo[i], -1);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, solve(i, (1 << i), words[i].length()));
        }

        System.out.println(ans);
    }

    private static int solve(int prev, int used, int score) {
        if (memo[prev][used] != -1) {
            return memo[prev][used];
        }

        int value = score;

        for (int i = 0; i < n; i++) {
            if ((used & (1 << i)) == 0) {

                char prevLastWord = words[prev].charAt(words[prev].length() - 1);
                char curFirstWord = words[i].charAt(0);

                if (prevLastWord == curFirstWord) {
                    value = Math.max(
                            value,
                            solve(i, /*현재 사용한 단어 사용 처리*/(used | (1 << i)), score + words[i].length())
                    );
                }
            }
        }

        return memo[prev][used] = value;
    }
}