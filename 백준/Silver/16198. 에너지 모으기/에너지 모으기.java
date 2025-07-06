import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        System.out.println(solve(0, list));
    }

    private static int solve(int sum, List<Integer> list) {
        if (list.size() <= 2) {
            return sum;
        }

        int max = 0;

        for (int i = 1; i < list.size() - 1; i++) {
            int energy = list.get(i - 1) * list.get(i + 1);

            List<Integer> temp = new ArrayList<>(list);
            temp.remove(i);

            int result = solve(sum + energy, temp);
            max = Math.max(max, result);
        }

        return max;
    }

}