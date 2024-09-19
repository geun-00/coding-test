import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] durability;
    static boolean[] robots;
    static int zero = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        robots = new boolean[n];
        durability = new int[2 * n];

        for (int i = 0; i < durability.length; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;

        while (zero < k) {
            count++;

            rotateBelt();
            moveRobots();

            if (durability[0] > 0) {
                robots[0] = true;
                durability[0]--;
                if (durability[0] == 0) {
                    zero++;
                }
            }
        }

        System.out.println(count);
    }

    private static void moveRobots() {
        for (int i = robots.length - 1; i > 0; i--) {

            if (robots[i - 1] && !robots[i] && durability[i] > 0) {
                robots[i] = true;
                robots[i - 1] = false;
                durability[i]--;
                if (durability[i] == 0) {
                    zero++;
                }
            }
        }
        robots[robots.length - 1] = false;
    }

    private static void rotateBelt() {
        int temp = durability[durability.length - 1];
        for (int i = durability.length - 1; i > 0; i--) {
            durability[i] = durability[i - 1];
        }
        durability[0] = temp;

        for (int i = robots.length - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }

        robots[0] = robots[robots.length - 1] = false;
    }
}
