import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    static char[] A, B;
    static int[][] dp;
    static Deque<Character> result = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        int lenA = A.length;
        int lenB = B.length;

        dp = new int[lenA + 1][lenB + 1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {

                if (A[i - 1] == B[j - 1]) { //두 문자가 같으면 왼쪽 대각선 + 1 저장
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                } else {                    //두 문자가 다르면 왼쪽과 위쪽 값 중 큰 값 저장
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[lenA][lenB]); //LCS 길이 출력

        getResult(lenA, lenB);

        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.append(result.pop());
        }
        
        System.out.print(sb);
    }

    private static void getResult(int r, int c) {
        if (r == 0 || c == 0) {
            return;
        }

        if (A[r - 1] == B[c - 1]) {
            result.push(A[r - 1]);
            getResult(r - 1, c - 1);
        } else {
            if (dp[r - 1][c] > dp[r][c - 1]) {
                getResult(r - 1, c);
            } else {
                getResult(r, c - 1);
            }
        }
    }
}
