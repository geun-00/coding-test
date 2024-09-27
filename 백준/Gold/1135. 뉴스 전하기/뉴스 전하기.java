import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        dp = new int[n];
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            dp[i] = -1;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (boss != -1) {
                tree[boss].add(i);
            }
        }

        System.out.println(solve(0));
    }

    public static int solve(int node) {
        // 이미 계산된 경우, DP 테이블 값을 바로 반환
        if (dp[node] != -1) {
            return dp[node];
        }

        // 자식 노드가 없으면, 0분 걸림
        if (tree[node].isEmpty()) {
            dp[node] = 0;
            return dp[node];
        }

        // 각 자식(부하)의 뉴스를 전달하는 데 걸리는 시간을 저장할 리스트
        ArrayList<Integer> times = new ArrayList<>();
        for (int child : tree[node]) {
            times.add(solve(child));
        }

        // 내림차순으로 정렬 (오래 걸리는 부하부터 먼저 전화해야 전체 시간을 줄일 수 있음)
        times.sort(Collections.reverseOrder());

        // 각 부하에게 뉴스를 전달하는데 걸리는 시간을 계산
        int maxTime = 0;
        for (int i = 0; i < times.size(); i++) {
            maxTime = Math.max(maxTime, times.get(i) + i + 1);
        }

        // 현재 직원의 뉴스를 전달하는데 걸리는 시간을 DP 테이블에 저장
        dp[node] = maxTime;
        return dp[node];
    }
}
