import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 100_000_000;

    static int[][] basicCost;
    static int[][] railroCost;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<String, Integer> cityIdx = new HashMap<>();

        int n = Integer.parseInt(st.nextToken()); //도시의 수
        int r = Integer.parseInt(st.nextToken()); //내일로 티켓 가격

        String[] cities = br.readLine().split(" "); //n개의 도시
        for (int i = 0; i < n; i++) {
            cityIdx.put(cities[i], i);
        }

        int m = Integer.parseInt(br.readLine());
        String[] travels = br.readLine().split(" "); //여행할 m개의 도시
        int[] route = new int[m];

        for (int i = 0; i < m; i++) {
            route[i] = cityIdx.get(travels[i]);
        }

        int k = Integer.parseInt(br.readLine()); //교통수단 수

        basicCost = new int[n][n];
        railroCost = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(basicCost[i], INF);
            Arrays.fill(railroCost[i], INF);
            basicCost[i][i] = 0;
            railroCost[i][i] = 0;
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            String type = st.nextToken();
            String s = st.nextToken();
            String e = st.nextToken();
            int cost = Integer.parseInt(st.nextToken());

            int u = cityIdx.get(s);
            int v = cityIdx.get(e);

            int railro = cost;
            if (type.equals("Mugunghwa") || type.equals("ITX-Saemaeul") || type.equals("ITX-Cheongchun")) {
                railro = 0;
            } else if (type.equals("S-Train") || type.equals("V-Train")) {
                railro = (int) Math.round(cost / 2.0);
            }

            basicCost[u][v] = Math.min(basicCost[u][v], cost);
            basicCost[v][u] = Math.min(basicCost[v][u], cost);

            railroCost[u][v] = Math.min(railroCost[u][v], railro);
            railroCost[v][u] = Math.min(railroCost[v][u], railro);
        }

        floydWarshall(basicCost, n);
        floydWarshall(railroCost, n);

        int basicTotalCost = calcTotalCost(basicCost, route, m);
        int railroTotalCost = calcTotalCost(railroCost, route, m) + r;

        if (basicTotalCost > railroTotalCost) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static int calcTotalCost(int[][] cost, int[] route, int m) {

        int total = 0;

        for (int i = 0; i < m - 1; i++) {
            if (cost[route[i]][route[i + 1]] == INF) {
                return INF;
            }
            total += cost[route[i]][route[i + 1]];
        }

        return total;
    }

    private static void floydWarshall(int[][] cost, int n) {

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    if (cost[s][e] > cost[s][k] + cost[k][e]) {
                        cost[s][e] = cost[s][k] + cost[k][e];
                    }
                }
            }
        }
    }
}
