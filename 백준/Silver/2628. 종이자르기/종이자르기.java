import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> rowCuts = new ArrayList<>();
        List<Integer> columnCuts = new ArrayList<>();

        int cuts = Integer.parseInt(br.readLine());
        for (int i = 0; i < cuts; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int line = Integer.parseInt(st.nextToken());

            if (num == 0) rowCuts.add(line);
            else columnCuts.add(line);
        }

        Collections.sort(rowCuts);
        Collections.sort(columnCuts);

        List<Integer> rows = getLines(rowCuts, m);
        List<Integer> columns = getLines(columnCuts, n);

        int ans = 0;

        for (Integer row : rows) {
            for (Integer column : columns) {
                ans = Math.max(ans, row * column);
            }
        }

        System.out.println(ans);
    }

    private static List<Integer> getLines(List<Integer> rowCuts, int last) {
        List<Integer> lines = new ArrayList<>();

        int prev = 0;
        for (int line : rowCuts) {
            lines.add(line - prev);
            prev = line;
        }
        lines.add(last - prev);

        return lines;
    }
}