import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] A = new int[n + 1];
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= k; i++) {
            dp[i][0] = 100000; //첫 번째 동전의 비교를 위해 각 0번째는 큰 수로 초기화
        }


        for (int i = 1; i <= k; i++) { //1원 ~ k원
            for (int j = 1; j <= n; j++) { //동전 가치 1번 ~ n번
                if (A[j] <= i) { //현재 동전 가치로 i원을 만들 수 있다.
                    // 이전 번 동전 가치의 최소 개수와 이전 경우의 수에서 현재 동전 가치를 한 번 추가할 때의 값을 비교한다.
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - A[j]][j] + 1);
                } else { //현재 동전 가치가 i원보다 크다면
                    dp[i][j] = dp[i][j - 1]; //최소 개수를 구할 수 없으므로 이전 번 경우의 수를 그대로 받는다.
                }
            }
        }

        //k원을 n번까지 탐색했는데 초깃값이 그대로면 불가능한 경우, 그렇지 않으면 값을 그대로 출력한다.
        System.out.println(dp[k][n] == 100000 ? -1 : dp[k][n]);
    }
}
