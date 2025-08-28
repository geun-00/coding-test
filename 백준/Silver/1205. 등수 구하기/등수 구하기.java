import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int taesu = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.println(1);
            return;
        }

        List<Integer> list = new ArrayList<>(n + 1);
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        if (n == p && taesu <= list.get(n - 1)) {
            System.out.println(-1);
            return;
        }

        list.add(taesu);
        list.sort((a, b) -> Integer.compare(b, a));

        int rank = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == taesu) {
                rank = i + 1;
                break;
            }
        }

        System.out.println(rank);
    }
}