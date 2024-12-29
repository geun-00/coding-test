import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int player = getPlayer(st.nextToken());

        Set<String> set = new HashSet<>();
        Set<String> sub = new HashSet<>();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (set.add(s)) {
                sub.add(s);
                if (sub.size() == player - 1) {
                    ans++;
                    sub.clear();
                }
            }
        }

        System.out.println(ans);
    }

    private static int getPlayer(String s) {
        switch (s) {
            case "Y": return 2;
            case "F": return 3;
            case "O": return 4;
        }
        return 0;
    }
}
