import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int n, k;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[k];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0);

        Collections.sort(list);

        System.out.println(list.get(list.size() - 1));
    }

    private static void solve(int num) {

        if (num <= n) {
            list.add(num);
        }

        if (num > n) {
            return;
        }
        
        for (int i = 0; i < k; i++) {
            solve(num * 10 + arr[i]);
        }
    }
}