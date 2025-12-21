import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n, ans;
    static int[] words, used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        words = new int[n];
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            int bit = 0;

            for (char ch : word.toCharArray()) {
                int a = ch - 'a';
                bit |= (1 << a);
            }

            words[i] = bit;
        }

        used = new int[1 << n];
        solve(0, 0, 0);
        System.out.println(ans);
    }

    private static void solve(int index, int bit, int use) {
        if (used[use] == 0 && bit == (1 << 26) - 1) {
            used[use] = 1;
            ans++;
        }

        if (index == n) {
            return;
        }

        solve(index + 1, bit | words[index], use | (1 << index));
        solve(index + 1, bit, use);
    }
}