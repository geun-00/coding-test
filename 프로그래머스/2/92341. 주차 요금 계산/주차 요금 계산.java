import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        Fee fee = new Fee(fees[0], fees[1], fees[2], fees[3]);

        TreeMap<String, Car> cars = new TreeMap<>();

        for (String record : records) {
            String[] tokens = record.split(" ");

            int time = parseTime(tokens[0]);
            String carNumber = tokens[1];
            boolean isIn = tokens[2].equals("IN");

            cars.putIfAbsent(carNumber, new Car());

            Car car = cars.get(carNumber);

            if (isIn) {
                car.in(time);
            } else {
                car.out(time);
            }
        }

        int endTime = parseTime("23:59");
        for (Car car : cars.values()) {
            car.out(endTime);
        }

        int[] ans = new int[cars.size()];

        for (int i = 0; i < ans.length; i++) {

            Map.Entry<String, Car> entry = cars.pollFirstEntry();
            Car car = entry.getValue();

            ans[i] = car.getCost(fee);
        }

        return ans;
    }
    
    public int parseTime(String s) {
        int hour = Integer.parseInt(s.substring(0, 2));
        int minute = Integer.parseInt(s.substring(3));

        return hour * 60 + minute;
    }
    
    static class Fee {

        int baseTime, baseFee;
        int unitTime, unitFee;

        public Fee(int baseTime, int baseFee, int unitTime, int unitFee) {
            this.baseTime = baseTime;
            this.baseFee = baseFee;
            this.unitTime = unitTime;
            this.unitFee = unitFee;
        }

        public int calculateCost(int time) {

            int fee = baseFee;

            int exceedTime = Math.max(0, time - baseTime);
            fee += Math.ceil(exceedTime * 1.0 / unitTime) * unitFee;

            return fee;
        }
    }

    static class Car {

        int lastInTime;
        int totalTime;

        public void in(int inTime) {
            this.lastInTime = inTime;
        }

        public void out(int outTime) {
            if (lastInTime == -1) return;

            this.totalTime += (outTime - lastInTime);
            this.lastInTime = -1;
        }

        public int getCost(Fee fee) {
            return fee.calculateCost(totalTime);
        }
    }
}