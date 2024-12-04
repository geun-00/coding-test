import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());

            HashMap<Character, ArrayList<Integer>> map = new HashMap<>();

            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);

                map.putIfAbsent(c, new ArrayList<>());
                map.get(c).add(i);
            }

            int min = 10001;
            int max = -1;

            for (ArrayList<Integer> list : map.values()) {

                if (list.size() < k) continue;

                for (int i = 0; i <= list.size() - k; i++) {
                    int len = list.get(i + k - 1) - list.get(i) + 1;
                    min = Math.min(min, len);
                    max = Math.max(max, len);
                }
            }

            if (max == -1) {
                sb.append(-1);
            } else {
                sb.append(min).append(" ").append(max);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}