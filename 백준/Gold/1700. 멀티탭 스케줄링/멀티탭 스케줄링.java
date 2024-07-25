import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] used = new int[k];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            used[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        HashSet<Integer> multiTap = new HashSet<>();
        for (int i = 0; i < k; i++) {

            if (!multiTap.contains(used[i]) && multiTap.size() >= n) {

                ArrayList<Integer> reuse = new ArrayList<>();
                HashSet<Integer> remain = new HashSet<>(multiTap);

                for (int j = i; j < k; j++) {

                    if (multiTap.contains(used[j]) && !reuse.contains(used[j])) {
                        reuse.add(used[j]);
                        remain.remove(used[j]);
                    }
                }

                if (reuse.size() >= n) {
                    multiTap.remove(reuse.get(reuse.size() - 1));
                } else if (!remain.isEmpty()) {
                    multiTap.remove(remain.iterator().next());
                }

                count++;
            }

            multiTap.add(used[i]);
        }

        System.out.println(count);
    }
}
