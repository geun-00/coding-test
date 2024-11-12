import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split("-");

        boolean first = true;

        int ans = 0;

        for (String str : s) {

            int sum = getSum(str);

            if (first) {
                first = false;
                ans += sum;
            } else {
                ans -= sum;
            }
        }

        System.out.println(ans);
    }

    private static int getSum(String str) {

        String[] split = str.split("\\+");

        int sum = 0;

        for (String s : split) {
            sum += Integer.parseInt(s);
        }

        return sum;
    }
}