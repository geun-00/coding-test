import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer>[] colors = new List[5001];
        for (int i = 0; i <= 5000; i++) {
            colors[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            colors[y].add(x);
        }

        for (int i = 0; i <= 5000; i++) {
            colors[i].sort(null);
        }

        int ans = 0;
        for (List<Integer> color : colors) {
            for (int i = 0; i < color.size(); i++) {
                if (i == 0) {
                    ans += color.get(i + 1) - color.get(i);
                } else if (i == color.size() - 1) {
                    ans += color.get(i) - color.get(i - 1);
                } else {
                    ans += Math.min((color.get(i) - color.get(i - 1)), (color.get(i + 1) - color.get(i)));
                }
            }
        }

        System.out.println(ans);
    }
}