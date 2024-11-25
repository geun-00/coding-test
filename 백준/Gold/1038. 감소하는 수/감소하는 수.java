import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 10; i++) {
            solve(9, 0, i, 0);
        }

        Collections.sort(list);

        if (n >= list.size()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(n));
        }
    }


    private static void solve(int start, int depth, int len, long num) {
        if (depth == len) {
            list.add(num);
            return;
        }

        for (int i = start; i >=0; i--) {
            solve(i - 1, depth + 1, len, num * 10 + i);
        }
    }
}