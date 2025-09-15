import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder ans = new StringBuilder();

        while (true) {
            String s = br.readLine();
            if ("*".equals(s)) {
                System.out.print(ans);
                break;
            }

            String result = " is surprising.";

            int n = s.length();

            iter:
            for (int i = 0; i <= n - 2; i++) {
                Set<String> set = new HashSet<>();

                for (int j = 0; j < n - (i + 1); j++) {
                    String temp = s.substring(j, j + 1) + s.charAt((i + j + 1));
                    if (!set.add(temp)) {
                        result = " is NOT surprising.";
                        break iter;
                    }
                }
            }

            ans.append(s).append(result).append("\n");
        }
    }
}