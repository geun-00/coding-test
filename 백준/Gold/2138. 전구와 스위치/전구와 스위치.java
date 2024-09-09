import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        boolean[] init = new boolean[n];
        boolean[] target = new boolean[n];

        String s = br.readLine();
        for (int i = 0; i < n; i++) {
            init[i] = s.charAt(i) == '1';
        }

        s = br.readLine();
        for (int i = 0; i < n; i++) {
            target[i] = s.charAt(i) == '1';
        }

        int firstOff = solve(init, target, n, false);
        int firstOn = solve(init, target, n, true);

        int result = Math.min(firstOff, firstOn);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static int solve(boolean[] init, boolean[] target, int n, boolean first) {

        int count = 0;
        boolean[] lights = init.clone();

        if (first) {
            toggle(lights, n, 0);
            count++;
        }

        for (int i = 1; i < n; i++) {
            if (lights[i - 1] != target[i - 1]) {
                toggle(lights, n, i);
                count++;
            }
        }

        if (!Arrays.equals(lights, target)) {
            return Integer.MAX_VALUE;
        }

        return count;
    }

    private static void toggle(boolean[] lights, int n, int idx) {
        lights[idx] = !lights[idx];

        if (idx > 0) {
            lights[idx - 1] = !lights[idx - 1];
        }
        if (idx < n - 1) {
            lights[idx + 1] = !lights[idx + 1];
        }
    }
}
