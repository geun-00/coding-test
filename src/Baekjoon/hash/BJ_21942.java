package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * <a href = "https://www.acmicpc.net/problem/21942">백준 21942번 - 해시 : 부품 대여장</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-21942%EB%B2%88-%EB%B6%80%ED%92%88-%EB%8C%80%EC%97%AC%EC%9E%A5">velog</a>
 *
 * @since 2024-10-28
 */
public class BJ_21942 {
    public static void main(String[] args) throws IOException {

        int[] arr = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

        HashMap<String, HashSet<String>> rentList = new HashMap<>(); //회원 대여 목록
        HashMap<String, HashMap<String, Integer>> rentInfos = new HashMap<>(); //회원 대여 정보
        TreeMap<String, Long> penalty = new TreeMap<>(); //회원마다 내야 할 벌금

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        String period = st.nextToken();             //대여기간
        int f = Integer.parseInt(st.nextToken());   //분당 벌금

        //대여 기한
        int period_day = Integer.parseInt(period.substring(0, 3));
        int period_hour = Integer.parseInt(period.substring(4, 6));
        int period_min = Integer.parseInt(period.substring(7));

        //대여 기한을 분단위로 변환
        int maxLent = getMin(period_day, period_hour, period_min);

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

            //대여/반납한 날을 분단위로 변환
            int days = arr[m] + d;
            int minute = getMin(days, hh, mm);

            String name = st.nextToken();     //부품 이름
            String nickname = st.nextToken(); //회원 닉네임

            if (rentList.get(nickname) == null) {
                rentList.put(nickname, new HashSet<>());
                rentInfos.put(nickname, new HashMap<>());
            }

            //대여하는 경우
            if (rentList.get(nickname).add(name)) {
                rentInfos.get(nickname).put(name, minute);
                continue;
            }

            //반납하는 경우
            int rentMin = rentInfos.get(nickname).get(name); //해당 부품을 대여한 날(분 단위)

            //대여 기한을 넘어서 반납한 경우
            if (minute > rentMin + maxLent) {
                int diff = minute - (rentMin + maxLent); //초과한 분
                int fee = diff * f;
                //초과한 분당 요금으로 회원 벌금 누적
                penalty.put(nickname, penalty.getOrDefault(nickname, 0L) + fee);
            }

            //반납했으므로 제거
            rentList.get(nickname).remove(name);
            rentInfos.get(nickname).remove(name);
        }

        //벌금을 낼 사람이 없는 경우
        if (penalty.keySet().isEmpty()) {
            System.out.println(-1);
            return;
        }

        for (String nickname : penalty.keySet()) {
            sb.append(nickname).append(" ").append(penalty.get(nickname)).append("\n");
        }

        System.out.print(sb);
    }

    //일/월/분을 분단위로 반환
    public static int getMin(int day, int hour, int min) {
        return (60 * 24 * day) + (60 * hour) + min;
    }
}