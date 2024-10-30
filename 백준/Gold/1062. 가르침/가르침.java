import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[] select = new boolean[26];
    static String[] words;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        if (k < 5) {
            System.out.println(0);
            return;
        }

        select['a' - 'a'] = true;
        select['n' - 'a'] = true;
        select['t' - 'a'] = true;
        select['i' - 'a'] = true;
        select['c' - 'a'] = true;

        teach(0, 0, k - 5);

        System.out.println(max);
    }

    private static void teach(int start, int depth, int limit) {
        if (depth == limit) {
            int read = readWords();
            max = Math.max(max, read);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!select[i]) {
                select[i] = true;
                teach(i + 1, depth + 1, limit);
                select[i] = false;
            }
        }
    }

    private static int readWords() {

        int count = 0;

        for (String w : words) {

            boolean possible = true;

            for (char c : w.toCharArray()) {
                if (!select[c - 'a']) {
                    possible = false;
                    break;
                }
            }

            if (possible) count++;
        }

        return count;
    }
}
