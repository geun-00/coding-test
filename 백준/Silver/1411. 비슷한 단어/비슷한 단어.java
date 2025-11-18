import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                count += solve(words[i], words[j]);
            }
        }

        System.out.println(count);
    }

    private static int solve(String a, String b) {
        Map<Character, Character> aTob = new HashMap<>();
        Map<Character, Character> bToa = new HashMap<>();

        for (int i = 0; i < a.length(); i++) {
            char ach = a.charAt(i);
            char bch = b.charAt(i);

            if (aTob.containsKey(ach)) {
                if (aTob.get(ach) != bch) {
                    return 0;
                }
            } else {
                aTob.put(ach, bch);
            }

            if (bToa.containsKey(bch)) {
                if (bToa.get(bch) != ach) {
                    return 0;
                }
            } else {
                bToa.put(bch, ach);
            }
        }

        return 1;
    }
}