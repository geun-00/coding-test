import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int min = Integer.MAX_VALUE;
    static int[] cond;
    static int[][] arr;
    static List<Integer> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        cond = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            cond[i] = Integer.parseInt(st.nextToken());
        }

        arr = new int[n][5];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, new int[5], n, new ArrayList<>());

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(min);
        for (int val : ans) {
            System.out.print(val + " ");
        }
    }

    private static void solve(int depth, int[] temp, int n, List<Integer> pick) {
        if (min < temp[4]) {
            return;
        }

        if (temp[0] >= cond[0] && temp[1] >= cond[1] && temp[2] >= cond[2] && temp[3] >= cond[3]) {
            if (min > temp[4]) {
                min = temp[4];
                ans = new ArrayList<>(pick);
            }
            return;
        }

        if (depth >= n) {
            return;
        }
        
        for (int i = 0; i < 5; i++) {
            temp[i] += arr[depth][i];
        }

        pick.add(depth + 1);
        solve(depth + 1, temp, n, pick);

        pick.remove(pick.size() - 1);
        for (int i = 0; i < 5; i++) {
            temp[i] -= arr[depth][i];
        }

        solve(depth + 1, temp, n, pick);
    }
}