import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        list.add(100_001);

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        list.sort(Collections.reverseOrder());
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                continue;
            }
            ans += list.get(i);
        }

        System.out.println(ans);
    }
}