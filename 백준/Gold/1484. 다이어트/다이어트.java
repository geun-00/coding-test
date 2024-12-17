import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());

        int s = 1, e = 1;
        List<Integer> ans = new ArrayList<>();

        while (e < g) {

            int diff = e * e - s * s;

            if (diff == g) {
                ans.add(e);
                e++;
            } else if (diff < g) {
                e++;
            } else {
                s++;
            }
        }

        if (ans.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int a : ans) {
                System.out.println(a);
            }
        }
    }
}