import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static String[] gears = new String[5];
    static boolean[] rotation;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 4; i++) {
            gears[i] = br.readLine();
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            rotation = new boolean[5];

            int num = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            char gear1_2 = gears[1].charAt(2);
            char gear2_2 = gears[2].charAt(2);
            char gear2_6 = gears[2].charAt(6);
            char gear3_2 = gears[3].charAt(2);
            char gear3_6 = gears[3].charAt(6);
            char gear4_6 = gears[4].charAt(6);

            rotate(num, r);

            switch (num) {
                case 1:
                    if (gear1_2 != gear2_6) {
                        rotate(2, -r);
                    }
                    if (gear2_2 != gear3_6 && rotation[2]) {
                        rotate(3, r);
                    }
                    if (gear3_2 != gear4_6 && rotation[3]) {
                        rotate(4, -r);
                    }
                    break;
                case 2:
                    if (gear1_2 != gear2_6) {
                        rotate(1, -r);
                    }
                    if (gear2_2 != gear3_6) {
                        rotate(3, -r);
                    }
                    if (gear3_2 != gear4_6 && rotation[3]) {
                        rotate(4, r);
                    }
                    break;
                case 3:
                    if (gear2_2 != gear3_6) {
                        rotate(2, -r);
                    }
                    if (gear3_2 != gear4_6) {
                        rotate(4, -r);
                    }
                    if (gear1_2 != gear2_6 && rotation[2]) {
                        rotate(1, r);
                    }
                    break;
                case 4:
                    if (gear3_2 != gear4_6) {
                        rotate(3, -r);
                    }
                    if (gear2_2 != gear3_6 && rotation[3]) {
                        rotate(2, r);
                    }
                    if (gear1_2 != gear2_6 && rotation[2]) {
                        rotate(1, -r);
                    }
                    break;
            }
        }

        System.out.println(((gears[1].charAt(0) - '0')) +
                ((gears[2].charAt(0) - '0') * 2) +
                ((gears[3].charAt(0) - '0') * 4) +
                ((gears[4].charAt(0) - '0') * 8)
        );
    }

    static void rotate(int num, int r) {

        rotation[num] = true;

        if (r == 1) { //시계 방향
            gears[num] = gears[num].charAt(7) + gears[num].substring(0, 7);
        } else if (r == -1) { //반시계 방향
            gears[num] = gears[num].substring(1) + gears[num].charAt(0);
        }
    }
}
