import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] times = br.readLine()
                           .split(" ");

        int S = parseTime(times[0]);
        int E = parseTime(times[1]);
        int Q = parseTime(times[2]);

        Set<String> set = new HashSet<>();
        int ans = 0;

        while (true) {

            String line = br.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }

            String[] arr = line.split(" ");
            int t = parseTime(arr[0]);

            if (t <= S) {
                set.add(arr[1]);
            } else if (E <= t && t <= Q) {
                if (set.contains(arr[1])) {
                    ans++;
                    set.remove(arr[1]);
                }
            }
        }

        System.out.println(ans);
    }

    static int parseTime(String time) {
        String[] arr = time.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
}