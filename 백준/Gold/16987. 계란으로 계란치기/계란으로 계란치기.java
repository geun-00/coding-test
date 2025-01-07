import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] eggs = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        solve(0, eggs);
        System.out.println(ans);
    }

    private static void solve(int index, int[][] eggs) {

        if (index == eggs.length) {
            int count = 0;
            for (int[] egg : eggs) {
                if(egg[0] <= 0) count++;
            }
            ans = Math.max(count, ans);
            return;
        }

        if (eggs[index][0] <= 0) {
            solve(index + 1, eggs);
            return;
        }

        boolean flag = false;

        for (int i = 0; i < eggs.length; i++) {

            if (i == index || eggs[i][0] <= 0) continue;

            flag = true;
            
            eggs[i][0] -= eggs[index][1];
            eggs[index][0] -= eggs[i][1];

            solve(index + 1, eggs);

            eggs[i][0] += eggs[index][1];
            eggs[index][0] += eggs[i][1];
        }

        if (!flag) {
            solve(index + 1, eggs);
        }
    }
}