import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] cranes = new int[n];
        for (int i = 0; i < n; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        int[] boxes = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cranes);
        Arrays.sort(boxes);

        if (boxes[m - 1] > cranes[n - 1]) {
            System.out.println(-1);
            return;
        }

        int[] craneIdx = new int[n];
        Arrays.fill(craneIdx, m - 1);

        boolean[] isMoved = new boolean[m];

        int count = 0;

        while (m > 0) {

            for (int i = n - 1; i >= 0; i--) {

                if (m == 0) break;

                for (int j = craneIdx[i]; j >= 0; j--) {
                    if (!isMoved[j]) {
                        if (cranes[i] >= boxes[j]) {
                            isMoved[j] = true;
                            m--;
                            break;
                        }
                        craneIdx[i]--;
                    }

                }
            }

            count++;
        }

        System.out.println(count);
    }
}
