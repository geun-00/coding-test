import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        TreeSet<Integer> landmarks = new TreeSet<>();

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] area = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            area[i] = Integer.parseInt(st.nextToken());
            if (area[i] == 1) {
                landmarks.add(i);
            }
        }

        int dohyun = 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());

            if (m == 1) {
                int num = Integer.parseInt(st.nextToken()) - 1;

                if (landmarks.contains(num)) {
                    landmarks.remove(num);
                } else {
                    landmarks.add(num);
                }
            } else if (m == 2) {
                int x = Integer.parseInt(st.nextToken());
                dohyun = (dohyun + x) % n;
            } else {
                if (landmarks.isEmpty()) {
                    sb.append(-1);
                } else {
                    Integer upper = landmarks.ceiling(dohyun);
                    if (upper != null) {
                        sb.append(upper - dohyun);
                    } else {
                        Integer lower = landmarks.first();
                        sb.append(n - dohyun + lower);
                    }
                }

                sb.append("\n");
            }
        }

        System.out.print(sb);
    }
}