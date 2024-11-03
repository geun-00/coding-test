package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16434">백준 16434번 - 이분 탐색 : 드래곤 앤 던전</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16434%EB%B2%88-%EB%93%9C%EB%9E%98%EA%B3%A4-%EC%95%A4-%EB%8D%98%EC%A0%84">velog</a>
 *
 * @since 2024-10-31
 */
public class BJ_16434 {

    static Room[] rooms;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long h = Long.parseLong(st.nextToken());

        rooms = new Room[n];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            boolean isMonster = Integer.parseInt(st.nextToken()) == 1;
            long atk = Long.parseLong(st.nextToken());
            long hp = Long.parseLong(st.nextToken());

            rooms[i] = new Room(isMonster, atk, hp);
        }

        long left = 1, right = Long.MAX_VALUE;
        long ans = 0;

        while (left <= right) {

            long mid = (left + right) / 2;

            //mid 값으로 HP 저장
            Knight knight = new Knight(mid, mid, h);

            //midHP로 가능하면 mid 값 임시 저장하고 더 적은 값을 확인하기 위해 right 조정
            if (goDungeon(knight)) {
                ans = mid;
                right = mid - 1;
            }
            //불가능하면 HP가 더 많아야 하므로 left 조정
            else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean goDungeon(Knight knight) {

        for (Room room : rooms) {

            //포션 있는 방
            if (!room.isMonster) {
                knight.increase(room.atk, room.hp);
                continue;
            }

            //용사가 몬스터를 처치하기 위해 필요한 공격 횟수
            long atkCnt = room.hp / knight.atk;
            if (room.hp % knight.atk != 0) {
                atkCnt++;
            }

            //용사가 선공이므로 몬스터는 죽기 전까지 용사보다 한대 덜 공격하게 된다.
            //용사의 생명력이 0 이하가 되면 전투 종료
            if (!knight.decrease(room.atk * (atkCnt - 1))) {
                return false;
            }
        }

        return true;
    }

    static class Room {

        boolean isMonster;
        long atk, hp;

        public Room(boolean isMonster, long atk, long hp) {
            this.isMonster = isMonster;
            this.atk = atk;
            this.hp = hp;
        }
    }

    static class Knight {

        long maxHP, curHP, atk;

        public Knight(long maxHP, long curHP, long atk) {
            this.maxHP = maxHP;
            this.curHP = curHP;
            this.atk = atk;
        }

        public void increase(long atk, long hp) {
            this.atk += atk;
            this.curHP = Math.min(maxHP, curHP + hp);
        }

        public boolean decrease(long damage) {
            curHP -= damage;
            return curHP > 0;
        }
    }
}
