import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int c;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //물건의 개수
        c = Integer.parseInt(st.nextToken()); //최대 c만큼

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Long> left = new ArrayList<>();
        ArrayList<Long> right = new ArrayList<>();

        solve(0, n / 2, 0, left);
        solve(n / 2, n, 0, right);

        long count = 0;
        
        right.sort(null);

        for (long num : left) {
            count += upperBound(c - num, right);
        }

        System.out.println(count);
    }

    private static long upperBound(long target, ArrayList<Long> list) {

        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static void solve(int s, int e, long sum, ArrayList<Long> list) {
        if (sum > c) {
            return;
        }
        if (s == e) {
            list.add(sum);
            return;
        }
        solve(s + 1, e, sum, list);
        solve(s + 1, e, sum + arr[s], list);
    }
}