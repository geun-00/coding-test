import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(arr[0]);

        for (int i = 1; i < n; i++) {
            int pos = binarySearch(arr[i]);
            if (pos < lis.size()) {
                lis.set(pos, arr[i]);
            } else {
                lis.add(arr[i]);
            }
        }

        System.out.println(lis.size());

    }

    private static int binarySearch(int val) {

        int s = 0;
        int e = lis.size() - 1;

        while (s <= e) {
            int mid = (s + e) / 2;
            if (lis.get(mid) < val) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        return s;
    }
}
