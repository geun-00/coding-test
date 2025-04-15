import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Long> positive = new ArrayList<>();
        List<Long> negative = new ArrayList<>();
        int ones = 0;

        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());

            if (num == 1) ones++;
            else if (num <= 0) negative.add(num);
            else positive.add(num);
        }

        Collections.sort(positive);
        Collections.sort(negative);

        long ans = 0;

        for (int i = positive.size() - 1; i >= 0; i -= 2) {
            if (i - 1 >= 0) {
                ans += (positive.get(i) * positive.get(i - 1));
            } else {
                ans += positive.get(i);
            }
        }

        for (int i = 0; i < negative.size(); i += 2) {
            if (i + 1 < negative.size()) {
                ans += (negative.get(i) * negative.get(i + 1));
            } else {
                ans += negative.get(i);
            }
        }

        ans += ones;
        System.out.println(ans);
    }
}
