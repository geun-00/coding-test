import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            char[] paper = br.readLine().toCharArray();
            System.out.println(solve(0, paper.length - 1, paper) ? "YES" : "NO");
        }
    }

    public static boolean solve(int start, int end, char[] paper) {
        if (start >= end) {
            return true;
        }

        int mid = (start + end) / 2;
        for (int i = start, j = end; i < mid; i++, j--) {
            if (paper[i] == paper[j]) {
                return false;
            }
        }

        return solve(start, mid - 1, paper) && solve(mid + 1, end, paper);
    }
}
