import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static class Problem implements Comparable<Problem>{
        int num, level;

        public Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level == o.level) {
                return this.num - o.num;
            }

            return this.level - o.level;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); //문제 개수

        TreeSet<Problem> problems = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
             st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            problems.add(new Problem(p, l));
            map.put(p, l);
        }

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            if ("add".equals(command)) {
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());

                problems.add(new Problem(p, l));
                map.put(p, l);
            } else if ("solved".equals(command)) {
                int p = Integer.parseInt(st.nextToken());

                problems.remove(new Problem(p, map.get(p)));
                map.remove(p);
            } else if ("recommend".equals(command)) {
                int x = Integer.parseInt(st.nextToken());

                if (x == 1) {
                    sb.append(problems.last().num).append("\n");
                } else if (x == -1) {
                    sb.append(problems.first().num).append("\n");
                }
            }
        }

        System.out.print(sb);
    }
}
