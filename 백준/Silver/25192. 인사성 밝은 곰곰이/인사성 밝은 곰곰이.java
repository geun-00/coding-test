import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        int ans = 0;
        HashSet<String> set = new HashSet<>();

        for (String s : arr) {

            if ("ENTER".equals(s)) {
                ans += set.size();
                set.clear();
            } else {
                set.add(s);
            }
        }

        System.out.println(ans + set.size());
    }
}