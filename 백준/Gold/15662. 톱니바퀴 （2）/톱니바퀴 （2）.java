import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] wheels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        wheels = new char[t][8];

        for (int i = 0; i < t; i++) {
            wheels[i] = br.readLine().toCharArray();
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            roll(num, dir);
        }

        int count = 0;
        for (int i = 0; i < t; i++) {
            if (wheels[i][0] == '1') {
                count++;
            }
        }

        System.out.println(count);
    }

    private static void roll(int num, int dir) {
        int[] rotate = new int[wheels.length];
        rotate[num] = dir;

        int d = dir;
        for (int left = num - 1; left >= 0; left--) {
            if (wheels[left][2] != wheels[left + 1][6]) {
                d = -d;
                rotate[left] = d;
            } else {
                break;
            }
        }

        d = dir;
        for (int right = num + 1; right < wheels.length; right++) {
            if (wheels[right - 1][2] != wheels[right][6]) {
                d = -d;
                rotate[right] = d;
            } else {
                break;
            }
        }

        for (int i = 0; i < wheels.length; i++) {
            if (rotate[i] > 0) {
                rollRight(i);
            } else if (rotate[i] < 0) {
                rollLeft(i);
            }
        }
    }

    public static void rollRight(int num) {
        char temp = wheels[num][7];
        for (int i = 7; i > 0; i--) {
            wheels[num][i] = wheels[num][i - 1];
        }
        wheels[num][0] = temp;
    }

    public static void rollLeft(int num) {
        char temp = wheels[num][0];
        for (int i = 0; i < 7; i++) {
            wheels[num][i] = wheels[num][i + 1];
        }
        wheels[num][7] = temp;
    }
}