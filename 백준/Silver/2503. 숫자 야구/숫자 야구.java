import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int k = 1; k <= 9; k++) {
                    if (i != j && j != k && i != k) {
                        list.add(i * 100 + j * 10 + k);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            ArrayList<Integer> temp = new ArrayList<>();

            for (int target : list) {
                if (check(target, num, strike, ball)) {
                    temp.add(target);
                }
            }

            list = temp;
        }

        System.out.println(list.size());
    }

    private static boolean check(int target, int num, int strike, int ball) {

        int t1 = target / 100;
        int t2 = target % 100 / 10;
        int t3 = target % 10;

        int n1 = num / 100;
        int n2 = num % 100 / 10;
        int n3 = num % 10;

        int s = 0, b = 0;

        if (t1 == n1) s++;
        if (t2 == n2) s++;
        if (t3 == n3) s++;

        if (t1 == n2 || t1 == n3) b++;
        if (t2 == n1 || t2 == n3) b++;
        if (t3 == n1 || t3 == n2) b++;

        return s == strike && b == ball;
    }
}