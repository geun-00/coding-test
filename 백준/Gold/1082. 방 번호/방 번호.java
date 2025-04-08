import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] price = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        // 비용이 작으면서 가장 큰 수 찾기
        int minCost = 50;
        int minNum = -1;
        for (int i = 0; i < n; i++) {
            if (price[i] <= minCost) {
                minCost = price[i];
                minNum = i;
            }
        }

        // 2. 최대 자릿수 계산
        int length = m / minCost;
        if (length == 0) {
            System.out.println(0); // 아무것도 못 사는 경우
            return;
        }

        // 3. minNum으로 초기화
        int[] result = new int[length];
        Arrays.fill(result, minNum);
        m -= minCost * length;

        // 4. 앞에서부터 가능한 가장 큰 숫자로 교체
        int s = 0;
        for (int i = 0; i < length; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (price[j] <= m + minCost) {
                    result[i] = j;
                    m += (minCost - price[j]);
                    break;
                }
            }

            if (result[s] == 0) {
                s++;
                m += minCost;
            }
        }

        // 5. 출력
        if (s==length) {
            System.out.println(0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s; i < length; i++) {
            sb.append(result[i]);
        }
        System.out.println(sb);
    }
}