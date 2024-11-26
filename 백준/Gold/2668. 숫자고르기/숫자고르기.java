import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int[] arr;
    static boolean[] visit;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
        }

        for (int i = 0; i < n; i++) {
            visit[i] = true;
            dfs(i, i);
            visit[i] = false;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(ans.size()).append("\n");

        for (int num : ans) {
            sb.append(num).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int now, int target) {

        if (!visit[arr[now]]) {
            visit[arr[now]] = true;
            dfs(arr[now], target);
        }

        if (arr[now] == target) {
            ans.add(target + 1);
        }

        visit[arr[now]] = false;
    }
}