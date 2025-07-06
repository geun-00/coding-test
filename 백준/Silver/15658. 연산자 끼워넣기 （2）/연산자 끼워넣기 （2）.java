import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] oper = new int[4];
        for (int i = 0; i < oper.length; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }

        solve(nums, oper, nums[0], 1);
        System.out.println(max);
        System.out.println(min);
    }

    private static void solve(int[] nums, int[] oper, int result, int i) {
        if (i == nums.length) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        if (oper[0] > 0) {
            oper[0]--;
            solve(nums, oper, result + nums[i], i + 1);
            oper[0]++;
        }
        if (oper[1] > 0) {
            oper[1]--;
            solve(nums, oper, result - nums[i], i + 1);
            oper[1]++;
        }
        if (oper[2] > 0) {
            oper[2]--;
            solve(nums, oper, result * nums[i], i + 1);
            oper[2]++;
        }
        if (oper[3] > 0) {
            oper[3]--;
            solve(nums, oper, result / nums[i], i + 1);
            oper[3]++;
        }
    }
}