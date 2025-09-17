import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Long> nums = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 10; i++) {
            dfs(i, i);
        }

        if (nums.size() < n) {
            System.out.println(-1);
            return;
        }

        nums.sort(null);

        System.out.println(nums.get(n - 1));
    }

    private static void dfs(long num, int lastNum) {
        nums.add(num);

        for (int i = 0; i < lastNum; i++) {
            dfs(num * 10 + i, i);
        }
    }
}