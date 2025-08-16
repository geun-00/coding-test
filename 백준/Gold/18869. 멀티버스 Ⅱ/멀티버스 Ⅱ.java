import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        Map<String, Integer> countMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int[] arr = new int[n];
            int[] sorted = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
                sorted[j] = arr[j];
            }

            Arrays.sort(sorted);

            Map<Integer, Integer> map = new HashMap<>();
            int rank = 0;

            for (int num : sorted) {
                if (!map.containsKey(num)) {
                    map.put(num, rank);
                    rank++;
                }
            }

            int[] temp = new int[n];
            for (int j = 0; j < n; j++) {
                temp[j] = map.get(arr[j]);
            }

            String key = Arrays.toString(temp);
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        }

        int ans = 0;
        for (int value : countMap.values()) {
            ans += value * (value - 1) / 2;
        }

        System.out.println(ans);
    }
}