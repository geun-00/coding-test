import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {

        int[] arr = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

        HashMap<String, HashSet<String>> rentList = new HashMap<>();        //회원 대여 목록
        HashMap<String, HashMap<String, Integer>> rentInfos = new HashMap<>(); //회원 대여 정보
        TreeMap<String, Long> penalty = new TreeMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        String period = st.nextToken();             //대여기간
        int f = Integer.parseInt(st.nextToken());   //분당 벌금

        int period_day = Integer.parseInt(period.substring(0, 3));
        int period_hour = Integer.parseInt(period.substring(4, 6));
        int period_min = Integer.parseInt(period.substring(7));

        int max = getMin(period_day, period_hour, period_min);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            //대여/반납 날짜
            String rentDay = st.nextToken();
            int m = Integer.parseInt(rentDay.substring(5, 7)) - 1;      //대여/반납 월
            int d = Integer.parseInt(rentDay.substring(8));   //대여/반납 일

            //대여/반납 시간
            String time = st.nextToken();
            int hh = Integer.parseInt(time.substring(0, 2));
            int mm = Integer.parseInt(time.substring(3));

            String name = st.nextToken(); //부품 이름
            String nickname = st.nextToken(); //회원 닉네임

            if (rentList.get(nickname) == null) {
                rentList.put(nickname, new HashSet<>());
                rentInfos.put(nickname, new HashMap<>());
            }

            if (rentList.get(nickname).add(name)) {
                int days = arr[m] + d;
                int min = getMin(days, hh, mm);
                rentInfos.get(nickname).put(name, min);
            } else {
                int days = arr[m] + d;
                int returnMin = getMin(days, hh, mm);
                int rentMin = rentInfos.get(nickname).get(name);

                if (returnMin > rentMin + max) {
                    int diff = returnMin - (rentMin + max);
                    long fee = (long) diff * f;
                    penalty.put(nickname, penalty.getOrDefault(nickname, 0L) + fee);
                }

                rentList.get(nickname).remove(name);
                rentInfos.get(nickname).remove(name);
            }

        }

        if (penalty.keySet().isEmpty()) {
            System.out.println(-1);
        } else {
            for (String nickname : penalty.keySet()) {
                sb.append(nickname).append(" ").append(penalty.get(nickname)).append("\n");
            }
        }

        System.out.print(sb);

    }

    public static int getMin(int day, int hour, int min) {
        return (60 * 24 * day) + (60 * hour) + min;
    }
}
