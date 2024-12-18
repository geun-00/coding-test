import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long k = sc.nextLong();

        System.out.println(solve(k));
    }

    private static int solve(long k) {

        if (k == 1) {
            return 0;
        }

        long len = 1;
        while (len * 2 < k) {
            len *= 2;
        }

        if (k <= len) {
            return solve(k);
        } else {
            return 1 - solve(k - len);
        }
    }
}