import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] trains = new int[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c;

            if (a == 1) {
                c = Integer.parseInt(st.nextToken());
                trains[b] |= (1 << c);
            }
            else if (a == 2) {
                c = Integer.parseInt(st.nextToken());
                trains[b] &= ~(1 << c);
            }
            else if (a == 3) {
                trains[b] <<= 1;
                trains[b] &= ((1 << 21) - 1);
            }
            else {
                trains[b] >>= 1;
                trains[b] &= ~1;
            }
        }

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            set.add(trains[i]);
        }

        System.out.println(set.size());
    }
}