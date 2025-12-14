import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] P = new int[n];
        int[] S = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        int[] init = P.clone();

        int count = 0;
        while (true) {
            if (canShare(P, n)) {
                System.out.println(count);
                return;
            }

            int[] temp = new int[n];
            for (int i = 0; i < n; i++) {
                temp[S[i]] = P[i];
            }
            P = temp;

            count++;

            if (Arrays.equals(init, P)) {
                System.out.println(-1);
                return;
            }
        }
    }

    private static boolean canShare(int[] p, int n) {
        for (int i = 0; i < n; i++) {
            if (p[i] != (i % 3)) {
                return false;
            }
        }

        return true;
    }
}