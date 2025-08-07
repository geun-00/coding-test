import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<int[]> sums = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                sums.add(new int[]{i, j, arr[i] + arr[j]});
            }
        }

        sums.sort(Comparator.comparingInt(row -> row[2]));

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < sums.size() - 1; i++) {
            int[] a = sums.get(i);
            int[] b = sums.get(i + 1);
            
            if (ans == 0) break;

            if (a[0] != b[0] && a[0] != b[1] && a[1] != b[0] && a[1] != b[1]) {
                ans = Math.min(ans, b[2] - a[2]);
            }
        }

        System.out.println(ans);
    }
}